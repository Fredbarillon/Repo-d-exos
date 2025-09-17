package org.app.models

import org.app.interfaces.ILendable
import org.app.exceptions.DocumentNonEmprunteException
import org.app.exceptions.DocumentDejaEmprunteException

class Books(title: String, editionYear: Int, val author: String, val nbPages: Int, val genre: Genre)
    : Document(title, editionYear), ILendable {

    constructor(title: String, author: String, genre: Genre) :
            this(title, 0, author, 100, genre)


    override fun displayInfos(){
        println("Livre: $title, $author, $editionYear, $nbPages pages, Genre: $genre")
    }

    private var borrowed: Boolean = false

    override fun lend() {
        println("Tentative d’emprunt du livre $title")
        if (borrowed) {
            throw DocumentDejaEmprunteException("Ce livre est déjà emprunté")
        }
        borrowed = true
        println("Emprunté avec succés: \"$title\".")
    }

    override fun bringBack() {
        if (!borrowed) {
            throw DocumentNonEmprunteException("Ce document n’a pas été emprunté !")
        }
        borrowed = false
        println("Retour du livre \"$title\"...\n" +
                "Le livre est maintenant disponible.")
    }

    override fun isBorrowed(): Boolean = borrowed
}