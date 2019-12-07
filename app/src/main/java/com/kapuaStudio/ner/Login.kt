package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity()
{
    /*Asociamos los componenetes de la activity para poder trabajar con ellos desde todas las
      posible funciones
    */



    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //inicializamos Firebase Auth
        auth = FirebaseAuth.getInstance()
        var tv_email = findViewById<TextView>(R.id.txt_mail)
        var btnMainMenu = findViewById<Button>(R.id.btn_log)
        var btnRegistrar=findViewById<Button>(R.id.registrar_log)
        /*
            Se busca una cadena de texto procedente de un intent, en caso de ser correcto significa
            que proviene del activity de registro, por lo cual se autorellena el texto de mail de
            usuario y aparece un Toast que indica que la cuenta se ha generado correctamente
         */
        val registroIntent=intent
        var mail = registroIntent.getStringExtra("dirEmail")
        if(mail!=null)
        {
            tv_email.setText(""+mail)
            Toast.makeText(this, "El registro ha concluido correctamente, ya puedes iniciar sesión",Toast.LENGTH_SHORT).show()
        }



        //Si se pulsa sobre el boton de Login esta función nos mandará a la comprobación de Firebase
        // para conceder el acceso
        btnMainMenu.setOnClickListener ( View.OnClickListener {
                view -> login()
        })

        //Si pulsamos sobre el boton de registrar esta función nos mandará directamente a la activity
        // de @registro.kt
        btnRegistrar.setOnClickListener{
                startActivity(Intent(this,Registro::class.java))
            finish()
            }
    }

    public override fun onStart()
    {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?)
    {

    }

    private fun login()
    {
        var tv_email = findViewById<TextView>(R.id.txt_mail)
        var tv_pass = findViewById<TextView>(R.id.txt_pass)
        //leemos el contenido de los dos textViews necesarios para el Login
        var email=tv_email.text.toString()
        var pass = tv_pass.text.toString()

    }
}
