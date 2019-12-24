package com.kapuaStudio.ner.data

class Salas {
    var nombre: String? = null
    var jugadores: String? = null
    var tematica: String? = null

    constructor()
    {}

    constructor(nombre: String?, jugadores: String?, tematica: String?)
    {
        this.nombre = nombre
        this.jugadores = jugadores
        this.tematica = tematica
    }


}