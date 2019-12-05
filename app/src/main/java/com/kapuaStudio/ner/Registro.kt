package com.kapuaStudio.ner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Registro : AppCompatActivity()
{

    private lateinit var auth: FirebaseAuth

    lateinit var  mDataBase : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth=FirebaseAuth.getInstance()
        val btnRegistro = findViewById<View>(R.id.btnRegistro) as Button
        btnRegistro.setOnClickListener{
            registrar()
        }


    }

    private fun registrar()
    {
        val txtnombre = findViewById<TextView>(R.id.reg_nombre)
        val txtmail = findViewById<TextView>(R.id.reg_email)
        val txtpass = findViewById<TextView>(R.id.reg_pass)

        var nombre = txtnombre.text.toString()
        var mail = txtmail.text.toString()
        var pass = txtpass.text.toString()

        if(!mail.isEmpty() && !pass.isEmpty())
        {
            auth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        val user = auth.currentUser
                        startActivity(Intent(this, MainMenu::class.java ))
                        finish()
                    }
                    else
                    {
                        Toast.makeText(this,"algo no va"+ (task.exception?.message ?: String() ),Toast.LENGTH_SHORT).show()
                    }
                }
        }


    }
}
