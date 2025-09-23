package com.example.correctionnotefrais.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object NoteFraisManager {
    private val notes = mutableListOf<NoteFrais>()
    private var nextId = 1

    fun ajouterNote(note: NoteFrais): Int {
        note.id = nextId++
        note.dateCreation = getCurrentDate()
        notes.add(note)
        return note.id
    }

    fun getNote(id: Int): NoteFrais? {
        return notes.find { it.id == id }
    }

    fun getToutesLesNotes(): List<NoteFrais> {
        return notes.toList()
    }

    fun updateNote(note: NoteFrais) {
        val index = notes.indexOfFirst { it.id == note.id }
        if (index != -1) {
            notes[index] = note
        }
    }

    fun supprimerNote(id: Int): Boolean {
        return notes.removeIf { it.id == id }
    }

    fun getNotesParStatut(status: String): List<NoteFrais> {
        return if (status == "Tous") notes.toList()
        else notes.filter { it.statut == status }
    }

    private fun getCurrentDate(): String {
        val formatedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatedDate.format(Date())
    }
}
