package com.kapuaStudio.ner.data

class Empresas
{
    var nombre : String? = null
    var ciudad : String? = null
    var provincia : String? = null
    var telf : String? = null
    var uid : String? = null
    var foto : String? = null

    constructor()
    {}
    constructor(nombre: String?, ciudad:String?, provincia:String?, telf:String?, uid:String?, foto:String? )
    {
        this.nombre = nombre
        this.ciudad = ciudad
        this.provincia = provincia
        this.telf = telf
        this.uid = uid
        this.foto = foto
    }

}

