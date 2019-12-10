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
    //preparamos variables para rellenar más adelante
    private lateinit var auth: FirebaseAuth
    private lateinit var  mDataBase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //tenemos la instancia de AUTH de firebase
        auth=FirebaseAuth.getInstance()
        //si pulsamos sobre registro
        val btnRegistro = findViewById<View>(R.id.btnRegistro) as Button
        btnRegistro.setOnClickListener{
            registrar()
        }
    }

    private fun registrar()
    {
        //Localizamos los EditText de la activity
        val txtmail = findViewById<TextView>(R.id.reg_email)
        val txtpass = findViewById<TextView>(R.id.reg_pass)
        //guardamos el contenido dentro de variables en el momento de pulsar el botón
        var mail = txtmail.text.toString()
        var pass = txtpass.text.toString()
        //comprobamos si ambos campos estan rellenos
        if(!mail.isEmpty() && !pass.isEmpty())
        {
            auth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener { task ->
                if (task.isSuccessful)// si es correcta la comprobación volvemos a la activity
                    //anterior pasanto la direción de mail por el intent
                {
                    val user = auth.currentUser
                    val intent:Intent= Intent(this, Login::class.java)
                    intent.putExtra("dirEmail", mail)
                    startActivity(intent)//lanzamos el intent
                    finish() // y cerramos este
                }
                else // si no... pues damos un mensaje de alerta
                {
                    Toast.makeText(this,"algo no va"+ (task.exception?.message ?: String() ),Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
