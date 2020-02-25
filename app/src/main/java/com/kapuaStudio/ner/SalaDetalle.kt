package com.kapuaStudio.ner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.data.SalaFullData
import kotlinx.android.synthetic.main.activity_sala_detalle.*

class SalaDetalle : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sala_detalle)

        auth= FirebaseAuth.getInstance()

        val nombreSalaSeleccionada:String = intent.getStringExtra("nomSala")

        rellenarDetalle(nombreSalaSeleccionada)
    }

    private  fun rellenarDetalle(nombre: String)
    {
        val refMaestra = FirebaseDatabase.getInstance().reference
        val refUid =  refMaestra.child("Salas").child(nombre)

        val valueEventListener = object  : ValueEventListener
        {
            override fun onDataChange(dataSnapshot: DataSnapshot)
            {
                val salaD = dataSnapshot.getValue(SalaFullData::class.java)
                lbl_nom_sala_detalle.setText((salaD?.nombre))
                lbl_jugadores_sala_detalle.setText(salaD?.jugadores)
                lbl_precio_sala_detalle.setText(salaD?.precio)
                lbl_tipo_sala_detalle.setText(salaD?.tipo)
                lbl_tematica_sala_detalle.setText(salaD?.tematica)
                lbl_descripcion_sala_detalle.setText(salaD?.descripcion)

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        refUid.addListenerForSingleValueEvent(valueEventListener)
    }
}
