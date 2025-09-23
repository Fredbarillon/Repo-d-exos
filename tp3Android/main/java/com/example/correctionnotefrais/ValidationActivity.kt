package com.example.correctionnotefrais

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.correctionnotefrais.databinding.ValidationActivityBinding

class ValidationActivity : AppCompatActivity() {

    private lateinit var binding: ValidationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ValidationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nom = intent.getStringExtra("nom_employe").orEmpty()
        val numero = intent.getStringExtra("numero_employe").orEmpty()
        val dep = intent.getStringExtra("departement").orEmpty()
        val type = intent.getStringExtra("type_frais").orEmpty()
        val montant = intent.getDoubleExtra("montant", 0.0)
        val tva = intent.getBooleanExtra("avec_tva", false)
        val urgence = intent.getStringExtra("urgence").orEmpty()

        val ttc = if (tva) montant * 1.2 else montant
        binding.tvRecap.text = "Nom: $nom\nNuméro: $numero\nDep: $dep\nType: $type\nHT: ${"%.2f".format(montant)}€\nTVA: ${if (tva) "oui" else "non"}\nTTC: ${"%.2f".format(ttc)}€\nUrgence: $urgence"

        val delais = arrayOf("24h", "48h", "1 semaine", "2 semaines")
        val spinAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, delais)
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerDelai.adapter = spinAdapter
        binding.spinnerDelai.setSelection(1)

        binding.btnValider.setOnClickListener {
            val statut = when (binding.radioGroupStatut.checkedRadioButtonId) {
                binding.radioApprouve.id -> "Approuvé"
                binding.radioRefuse.id -> "Refusé"
                else -> "En attente"
            }
            val res = Intent().apply {
                putExtra("statut_validation", statut)
                putExtra("commentaires", binding.etCommentaires.text.toString().trim())
                putExtra("remboursement_prioritaire", binding.checkPrioritaire.isChecked)
                putExtra("delai_traitement", binding.spinnerDelai.selectedItem?.toString() ?: "48h")
                putExtra("action_validation", "valider")
            }
            setResult(RESULT_OK, res)
            finish()
        }

        binding.btnModifier.setOnClickListener {
            val res = Intent().apply {
                putExtra("statut_validation", "En attente")
                putExtra("commentaires", binding.etCommentaires.text.toString().trim())
                putExtra("remboursement_prioritaire", binding.checkPrioritaire.isChecked)
                putExtra("delai_traitement", binding.spinnerDelai.selectedItem?.toString() ?: "48h")
                putExtra("action_validation", "modifier")
            }
            setResult(RESULT_OK, res)
            finish()
        }

        binding.btnAnnuler.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}
