package org.app.models

abstract class Document(val title: String,val editionYear: Int){
    abstract fun displayInfos()
}