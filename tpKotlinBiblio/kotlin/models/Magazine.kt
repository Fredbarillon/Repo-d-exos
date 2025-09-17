package org.app.models

import org.app.interfaces.ISearchable

class Magazine(val serial: Int, val title: String,editionYear: Int) :Document(title,editionYear), ISearchable {
    override fun displayInfos() {
        println("some infos about the magazine")
    }
    override fun search() {

    }

}