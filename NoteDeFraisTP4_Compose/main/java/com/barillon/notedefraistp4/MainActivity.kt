package com.barillon.notedefraistp4


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.barillon.notedefraistp4.databinding.ActivityMainBinding
import com.barillon.notedefraistp4.model.NoteFrais
import com.barillon.notedefraistp4.model.NoteFraisManager
import com.barillon.notedefraistp4.screens.HistoriqueActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val noteFrais = NoteFrais()

    companion object {
        const val REQUEST_CODE_VALIDATION = 1001
    }

    private val typesFrais = arrayOf("Transport", "Hébergement", "Repas", "Matériel", "Formation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
        setupListeners()
        updateNote()
    }

    private fun setupViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, typesFrais)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTypeFrais.adapter = adapter

        binding.notePreview.setBackgroundColor(Color.parseColor(noteFrais.getCouleurUrgence()))
        binding.progressBarBudget.max = 100
    }

    private fun setupListeners() {
        binding.editTextNom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                noteFrais.nomEmploye = s.toString()
                validateNom()
                updateNote()
            }
        })

        binding.editTextNumero.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                noteFrais.numeroEmploye = s.toString()
                validateNumero()
                updateNote()
            }
        })

        binding.radioGroupDepartement.setOnCheckedChangeListener { _, checkedId ->
            noteFrais.departement = when (checkedId) {
                R.id.radioBtnCommercial -> "Commercial"
                R.id.radioBtnMarketing -> "Marketing"
                R.id.radioBtnIT -> "IT"
                R.id.radioBtnRH -> "RH"
                else -> ""
            }
            updateNote()
        }

        binding.spinnerTypeFrais.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                noteFrais.typeFrais = typesFrais[position]
                updateNote()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.seekBarMontant.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                noteFrais.montant = 10.0 + progress // De 10€ à 500€
                binding.textViewMontant.text = "Montant : ${noteFrais.montant.toInt()}€"
                updateNote()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.checkBoxRecurrent.setOnCheckedChangeListener { _, isChecked ->
            noteFrais.fraisRecurrent = isChecked
            updateNote()
        }

        binding.switchTVA.setOnCheckedChangeListener { _, isChecked ->
            noteFrais.avecTVA = isChecked
            updateNote()
        }

        binding.checkBoxJustificatif.setOnCheckedChangeListener { _, isChecked ->
            noteFrais.justificatifFourni = isChecked
            updateNote()
        }

        binding.radioGroupUrgence.setOnCheckedChangeListener { _, checkedId ->
            noteFrais.urgence = when (checkedId) {
                R.id.radioBtnNormal -> "Normal"
                R.id.radioBtnUrgent -> "Urgent"
                R.id.radioBtnTresUrgent -> "Très urgent"
                else -> "Normal"
            }
            binding.notePreview.setBackgroundColor(Color.parseColor(noteFrais.getCouleurUrgence()))
        }

        binding.btnCalculer.setOnClickListener {
            val montantTTC = noteFrais.calculerMontantTTC()
            val message = "Montant calculé: ${String.format("%.2f", montantTTC)}€"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        binding.btnSoumettre.setOnClickListener {
            if (validateForm()) {
                val intent = Intent(this, ValidationActivity::class.java)

                intent.putExtra("nom_employe", noteFrais.nomEmploye)
                intent.putExtra("numero_employe", noteFrais.numeroEmploye)
                intent.putExtra("departement", noteFrais.departement)
                intent.putExtra("type_frais", noteFrais.typeFrais)
                intent.putExtra("montant", noteFrais.montant)
                intent.putExtra("avec_tva", noteFrais.avecTVA)
                intent.putExtra("frais_recurrent", noteFrais.fraisRecurrent)
                intent.putExtra("justificatif_fourni", noteFrais.justificatifFourni)
                intent.putExtra("urgence", noteFrais.urgence)

                startActivityForResult(intent, REQUEST_CODE_VALIDATION)
            }
        }

        binding.btnReset.setOnClickListener {
            resetForm()
        }

        binding.btnHistorique.setOnClickListener {
            val intent = Intent(this, HistoriqueActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateNom(): Boolean {
        return if (noteFrais.isNomValide()) {
            binding.textViewErreurNom.visibility = View.GONE
            true
        } else {
            binding.textViewErreurNom.text = "Le nom doit contenir au moins 2 mots"
            binding.textViewErreurNom.visibility = View.VISIBLE
            false
        }
    }

    private fun validateNumero(): Boolean {
        return if (noteFrais.isNumeroValide()) {
            binding.textViewErreurNumero.visibility = View.GONE
            true
        } else {
            binding.textViewErreurNumero.text = "Le numéro doit contenir exactement 5 chiffres"
            binding.textViewErreurNumero.visibility = View.VISIBLE
            false
        }
    }

    private fun validateForm(): Boolean {
        val isNomValid = noteFrais.isNomValide()
        val isNumeroValid = noteFrais.isNumeroValide()
        val hasDepartement = noteFrais.departement.isNotEmpty()

        val isFormValid = isNomValid && isNumeroValid && hasDepartement
        binding.btnSoumettre.isEnabled = isFormValid

        return isFormValid
    }

    private fun updateNote() {
        binding.textViewNomNote.text = if (noteFrais.nomEmploye.isNotBlank()) {
            noteFrais.nomEmploye
        } else {
            "Nom employé"
        }

        binding.textViewNumeroNote.text = if (noteFrais.numeroEmploye.isNotBlank()) {
            "N° ${noteFrais.numeroEmploye}"
        } else {
            "N° 00000"
        }

        binding.textViewDepartementNote.text = if (noteFrais.departement.isNotEmpty()) {
            noteFrais.departement
        } else {
            "Département non sélectionné"
        }

        binding.textViewTypeFraisNote.text = noteFrais.typeFrais

        // Mise à jour des montants
        binding.textViewMontantNote.text = "Montant HT: ${String.format("%.2f", noteFrais.montant)}€"
        val montantTTC = noteFrais.calculerMontantTTC()
        binding.textViewMontantTTC.text = "Montant TTC: ${String.format("%.2f", montantTTC)}€"

        val pourcentageBudget = ((noteFrais.montant / 1000.0) * 100).toInt().coerceAtMost(100)
        binding.progressBarBudget.progress = pourcentageBudget

        validateForm()
    }

    private fun resetForm() {
        noteFrais.nomEmploye = ""
        noteFrais.numeroEmploye = ""
        noteFrais.departement = ""
        noteFrais.typeFrais = typesFrais[0]
        noteFrais.montant = 10.0
        noteFrais.avecTVA = false
        noteFrais.fraisRecurrent = false
        noteFrais.justificatifFourni = false
        noteFrais.urgence = "Normal"

        binding.editTextNom.text.clear()
        binding.editTextNumero.text.clear()
        binding.radioGroupDepartement.clearCheck()
        binding.spinnerTypeFrais.setSelection(0)
        binding.seekBarMontant.progress = 0
        binding.textViewMontant.text = "Montant : 10€"
        binding.checkBoxRecurrent.isChecked = false
        binding.switchTVA.isChecked = false
        binding.checkBoxJustificatif.isChecked = false
        binding.radioGroupUrgence.check(R.id.radioBtnNormal)

        binding.textViewErreurNom.visibility = View.GONE
        binding.textViewErreurNumero.visibility = View.GONE

        binding.notePreview.setBackgroundColor(Color.parseColor(noteFrais.getCouleurUrgence()))

        updateNote()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_VALIDATION && resultCode == RESULT_OK && data != null) {
            val statut = data.getStringExtra("statut_validation") ?: "En attente"
            val commentaires = data.getStringExtra("commentaires_manager") ?: ""
            val prioritaire = data.getBooleanExtra("remboursement_prioritaire", false)
            val delai = data.getStringExtra("delai_traitement") ?: "48h"

            noteFrais.statut = statut
            noteFrais.commentairesManager = commentaires
            noteFrais.remboursementPrioritaire = prioritaire
            noteFrais.delaiTraitement = delai

            NoteFraisManager.ajouterNote(noteFrais.copy())

            val message = buildString {
                append("Note de frais $statut")
                if (prioritaire) append(" (prioritaire)")
                if (commentaires.isNotEmpty()) {
                    append("\nCommentaire: $commentaires")
                }
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            if (statut == "Approuvé") {
                resetForm()
            }
        }
    }
}