package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.data.Users

class MenuPrincipal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userName : TextView
    private lateinit var lUid : String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //declaramos los botones, por hacer algo
        var btn_empresas= findViewById<Button>(R.id.btn_empresas_prin)
        var btn_usuario = findViewById<Button>(R.id.btn_usuario_prin)
        var card_test :CardView = findViewById(R.id.cardtest)


        //vamos a ver que pasa si pulsas un cardView
        card_test.setOnClickListener { startActivity(Intent(this,Empresas::class.java)) }



        //los botones van haciendo cosas
        btn_empresas.setOnClickListener{
            startActivity(Intent(this,Empresas::class.java))
        }

        btn_usuario.setOnClickListener{
            startActivity(Intent(this,Usuario::class.java))
        }

        auth=FirebaseAuth.getInstance()
        userName = findViewById(R.id.princNombreUser)
        lUid= auth.uid.toString()

        rellenarUsuario()

    }

    private fun rellenarUsuario()
    {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuario").child(lUid)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Users::class.java)
                Log.d("TAG", "Value is: $value")
                if (value != null) {
                    userName.text=value.nombre.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })

    }
}
