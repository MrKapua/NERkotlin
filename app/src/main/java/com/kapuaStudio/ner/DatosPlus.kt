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

        val btn_aceptar = findViewById<Button>(id.R.btn_datoPlusNickOK)
        val btn_cancelar = findViewById<Button>(id.R.btn_datoPlusNickCancel)

        btn_aceptar.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            finish()
        }

        btn_cancelar.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        }

    }
}
