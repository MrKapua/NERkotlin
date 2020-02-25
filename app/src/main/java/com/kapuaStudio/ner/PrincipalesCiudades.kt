package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.core.view.get
import com.kapuaStudio.ner.data.ciudadesPrincipales
import kotlinx.android.synthetic.main.activity_princiaples_ciudades.*

class PrincipalesCiudades : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_princiaples_ciudades)

        val city1 = ciudadesPrincipales("Madrid")
        val city2 = ciudadesPrincipales("Barcelona")
        val city3 = ciudadesPrincipales("Valencia")
        val city4 = ciudadesPrincipales("Sevilla")
        val city5 = ciudadesPrincipales("Zaragoza")
        val city6 = ciudadesPrincipales("Málaga")
        val city7 = ciudadesPrincipales("Bilbao")
        val city8 = ciudadesPrincipales("Alicante")
        val city9 = ciudadesPrincipales("Córdoba")
        val city10 = ciudadesPrincipales("Cáceres")

        val ciudadesPrincipales = listOf(city1, city2, city3, city4, city5, city6, city7, city8, city9, city10)

        val adapter = Ciudad_adaptador(this, ciudadesPrincipales)

        listaCities.adapter = adapter

        listaCities.setOnItemClickListener { parent, view, position, id ->
            Log.e("hola", ciudadesPrincipales[position].nombre)
            val intent = Intent(this, EmpresasActivity::class.java)
            intent.putExtra("tipo", 1)
            intent.putExtra("nombre", ciudadesPrincipales[position].nombre)
            startActivity(intent)

        }

    }
}

