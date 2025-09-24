package com.barillon.notedefraistp4.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.barillon.notedefraistp4.model.NoteFraisManager

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val noteId = intent.getIntExtra("noteId", -1)
        val note = NoteFraisManager.getNote(noteId)

        setContent {
            if (note != null) {
                DetailScreen(
                    note = note,
                    onBack = { finish() },
                    onModifier = {  val resultIntent = Intent()
                        resultIntent.putExtra("action", "modifie")
                        resultIntent.putExtra("nom_employe", note.nomEmploye)
                        resultIntent.putExtra("numero_employe", note.numeroEmploye)
                        resultIntent.putExtra("departement", note.departement)
                        resultIntent.putExtra("type_frais", note.typeFrais)
                        resultIntent.putExtra("montant", note.montant)
                        resultIntent.putExtra("avec_tva", note.avecTVA)
                        resultIntent.putExtra("frais_recurrent", note.fraisRecurrent)
                        resultIntent.putExtra("justificatif_fourni", note.justificatifFourni)
                        resultIntent.putExtra("urgence", note.urgence)

                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },

                    onSupprimer = {
                        NoteFraisManager.supprimerNote(noteId)

                        val resultIntent = Intent()
                        resultIntent.putExtra("action", "supprime")
                        resultIntent.putExtra("message", "Note #$noteId supprimÃ©e avec succÃ¨s")
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                )
            } else {
                finish()
            }
        }
    }
}
@Composable
fun DetailScreen()