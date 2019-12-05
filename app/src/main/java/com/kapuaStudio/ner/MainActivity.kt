package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var boton : Button =findViewById(R.id.botonIr)

        boton.setOnClickListener {
            val salto = Intent(this, Login::class.java)
            startActivity(salto)
        }
    }
}
