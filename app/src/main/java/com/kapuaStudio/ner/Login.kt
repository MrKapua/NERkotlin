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
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        auth = FirebaseAuth.getInstance()


        var btnMainMenu = findViewById<Button>(R.id.btn_log)
        var btnRegistrar=findViewById<Button>(R.id.registrar_log)

        btnMainMenu.setOnClickListener ( View.OnClickListener {
                view -> login()
        })
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
        //var tv_email = findViewById<TextView>(R.id.txt_mail)
        // var tv_pass = findViewById<TextView>(R.id.txt_pass)

        //var nombre = tv_nombre.text.toString()
        //var email=tv_email.text.toString()
        //var pass = tv_pass.text.toString()

    }

    private fun Registrar()
    {
        startActivity(Intent(this, Registro::class.java))
    }
}
