package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.kapuaStudio.ner.data.ciudadesPrincipales

class Busqueda : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busqueda)
        var provincia :String = ""
        var buscar = findViewById<Button>(R.id.btn_buscar)

        var spinner: Spinner = findViewById(R.id.spinnerProvincias)
        var provincias = resources.getStringArray(R.array.provincias)

        var adapter=ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinner.adapter = adapter

        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?)
            {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {
                Log.e("hola", provincias[position])
                provincia= provincias[position]
            }

        }

        buscar.setOnClickListener{
            val intent = Intent(this, EmpresasActivity::class.java)
            intent.putExtra("tipo", 2)
            intent.putExtra("nombre", provincia)
            startActivity(intent)
        }


    }
}
