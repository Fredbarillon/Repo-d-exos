package org.app.interfaces

interface ILendable {
    fun lend();
    fun bringBack();
    fun isBorrowed() : Boolean;
}