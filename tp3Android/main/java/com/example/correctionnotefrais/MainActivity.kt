package com.example.correctionnotefrais

import android.content.ActivityNotFoundException
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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.correctionnotefrais.databinding.ActivityMainBinding
import com.example.correctionnotefrais.model.NoteFrais
import com.example.correctionnotefrais.model.NoteFraisManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val noteFrais = NoteFrais()
    private val typesFrais = arrayOf("Transport", "Hébergement", "Repas", "Matériel", "Formation")

    private var noteID: Int? = null
    private val validationCode = 12

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setupViews()
        setupListeners()
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
                noteFrais.montant = 10.0 + progress // 10€ à 500€
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
            if (!validateForm()) {
                Toast.makeText(this, "Veuillez remplir tous les champs obligatoires", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val id = NoteFraisManager.ajouterNote(noteFrais.copy())
            noteID = id

            val intent = Intent().apply {
                setClassName(packageName, "com.example.correctionnotefrais.ValidationActivity")
                putExtra("note_id", id)
                putExtra("nom_employe", noteFrais.nomEmploye)
                putExtra("numero_employe", noteFrais.numeroEmploye)
                putExtra("departement", noteFrais.departement)
                putExtra("type_frais", noteFrais.typeFrais)
                putExtra("montant", noteFrais.montant)
                putExtra("avec_tva", noteFrais.avecTVA)
                putExtra("urgence", noteFrais.urgence)
            }

            try {
                @Suppress("DEPRECATION")
                startActivityForResult(intent, validationCode)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Écran de validation à faire.", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnReset.setOnClickListener {
            resetForm()
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
        // Mise à jour nom
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

        binding.textViewMontantNote.text = "Montant HT: ${String.format("%.2f", noteFrais.montant)}€"
        val montantTTC = noteFrais.calculerMontantTTC()
        binding.textViewMontantTTC.text = "Montant TTC: ${String.format("%.2f", montantTTC)}€"

        binding.progressBarBudget.progress = noteFrais.getPourcentageBudget()

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
        if (requestCode == validationCode) {
            noteDeFraisUpdate(resultCode, data)
        }
    }

    private fun noteDeFraisUpdate(resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK || data == null) return

        val statut = data.getStringExtra("statut_validation") ?: return
        val commentaires = data.getStringExtra("commentaires").orEmpty()
        val prioritaire = data.getBooleanExtra("remboursement_prioritaire", false)
        val delai = data.getStringExtra("delai_traitement").orEmpty()
        val action = data.getStringExtra("action_validation").orEmpty()

        noteID?.let { id ->
            NoteFraisManager.getNote(id)?.let { n ->
                n.statut = statut
                n.remboursementPrioritaire = prioritaire
                n.delaiTraitement = delai
                n.dateValidation = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                    .format(java.util.Date())
                NoteFraisManager.updateNote(n)
            }
        }

        Toast.makeText(this, "Retour: $statut (${if (action.isNotBlank()) action else "ok"})", Toast.LENGTH_SHORT).show()
    }
}