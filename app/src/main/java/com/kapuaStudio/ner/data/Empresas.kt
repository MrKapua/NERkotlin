package com.kapuaStudio.ner.data

class Empresas
{
    var nombre : String? = null
    var ciudad : String? = null
    var provincia : String? = null
    var telf : String? = null

    constructor()
    {}
    constructor(nombre: String?, ciudad:String?, provincia:String?, telf:String? )
    {
        this.nombre = nombre
        this.ciudad = ciudad
        this.provincia = provincia
        this.telf = telf
    }

}

