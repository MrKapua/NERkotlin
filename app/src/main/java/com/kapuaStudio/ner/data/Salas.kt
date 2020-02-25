package com.kapuaStudio.ner.data

class Salas
{
    var empresa : String? = null
    var nombre : String? = null
    var jugadores : String? = null
    var tematica : String? = null
    var precio : String? = null
    var tiempo : String? = null
    var tipo : String? = null
    var reserva : String? = null
    var uid: String? = null

    constructor()
    {}
    constructor(empresa: String?, nombre: String?, jugadores: String?, tematica: String?, precio: String?, tiempo: String?, tipo: String?, reserva: String?, uid: String)
    {
        this.empresa = empresa
        this.nombre = nombre
        this.jugadores = jugadores
        this.tematica = tematica
        this.precio = precio
        this.tiempo = tiempo
        this.tipo = tipo
        this.reserva = reserva
        this.uid = uid
    }
}