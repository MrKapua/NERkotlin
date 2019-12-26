package com.kapuaStudio.ner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.data.Users

class Usuario : AppCompatActivity()
{
    private lateinit var nombre_tv : TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var lUid : String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        nombre_tv=findViewById(R.id.tv_nombre_usu)

        //vamos a por los datos de Firebase
        auth=FirebaseAuth.getInstance()
        lUid=auth.uid.toString()//¿quién soy?



        rellenarDatos()

    }

    private fun rellenarDatos() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuario").child(lUid)
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                var padre: String? = dataSnapshot.key
                val value = dataSnapshot.getValue(Users::class.java)
                Log.d("TAG", "Value is: $value")
                if (value != null) {
                    nombre_tv.setText(value.nombre.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException())
            }
        })
    }


}
