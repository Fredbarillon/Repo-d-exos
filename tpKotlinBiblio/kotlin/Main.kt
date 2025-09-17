package org.app

import org.app.models.Document
import org.app.models.Genre
import org.app.models.Magazine
import org.app.models.Books

fun main() {
 val library: MutableList<Document> = mutableListOf()

        library.add(Books("Livre 1",1689,"Auteur1", 250, Genre.ROMAN))
        library.add(Books("Livre 2",  1912,"Auteur2", 300,Genre.SCIENCE_FICTION))
        library.add(Books("Livre 3", "Auteur3", Genre.FANTASTIQUE))
        library.add(Books("Livre 4",  2022,"Auteur4", 15, Genre.BIOGRAPHIE))
        library.add(Books("Livre 5", 1955,"Auteur5", 462,Genre.HISTORIQUE))

        // 3 magazines (number, title, editionYear)
        library.add(Magazine(1, "Magazine 1", 2023))
        library.add(Magazine(2, "Magazine 2", 2024))
        library.add(Magazine(3, "Magazine 3", 2025))

    library.forEach { it.displayInfos() }

    val book =library[0] as Books

    book.displayInfos()

    try {
        book.lend()
    }catch (e: Exception){
        println("Erreur: ${e.message}")
    }



}