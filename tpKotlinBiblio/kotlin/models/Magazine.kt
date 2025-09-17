package org.app.models

import org.app.interfaces.ISearchable
import org.app.exceptions.DocumentDejaEmprunteException
class Magazine(val serial: Int, title: String,editionYear: Int)
    :Document(title,editionYear), ISearchable {

    override fun displayInfos() {
        println("Magazine: \"$title\", n°$serial, $editionYear")
    }

    val available: Boolean = true

    override fun search() {
        if (!available) {
            throw DocumentDejaEmprunteException("Le magazine \"$title\" n'est pas consultable pour le moment.")
        }
        println("Consultation du magazine \"$title\"...\nVous consultez ce document.")
    }

}