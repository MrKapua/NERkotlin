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
import kotlinx.android.synthetic.main.activity_usuario.*

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

    private fun rellenarDatos()
    {
        val keyUid = FirebaseAuth.getInstance().currentUser!!.uid
        val refMaestra = FirebaseDatabase.getInstance().reference
        val refUid =  refMaestra.child("Usuario").child(keyUid)

        val valueEventListener = object  :ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(Users::class.java)
                //Log.e("hola", "hola soy...." + user!!.nombre)
                tv_nombre_usu.text=user!!.nombre
                tv_apellido_usu.text=user!!.apellido
                tv_mail_usu.text=user!!.uid
                tv_provincia_usu.text=user!!.provincia

            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        refUid.addListenerForSingleValueEvent(valueEventListener)

    }


}
