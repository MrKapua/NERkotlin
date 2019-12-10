package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DatosPlus : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_plus)
        // Localizamos los botones basicos de la activity
        val btn_aceptar = findViewById<Button>(R.id.btn_datoPlusNickOK)
        val btn_cancelar = findViewById<Button>(R.id.btn_datoPlusNickCancel)

        //Si pulsamos sobre el boton de aceptar
        btn_aceptar.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            finish()
        }
        //Si es sobre el botón de cancelar
        btn_cancelar.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}
