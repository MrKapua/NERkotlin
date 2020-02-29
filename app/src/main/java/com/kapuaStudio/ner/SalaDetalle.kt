package com.kapuaStudio.ner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.data.SalaFullData
import kotlinx.android.synthetic.main.activity_sala_detalle.*
import android.content.Intent
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri


class SalaDetalle : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sala_detalle)

        auth= FirebaseAuth.getInstance()

        val nombreSalaSeleccionada:String = intent.getStringExtra("nomSala")
        var botonReserva : Button = findViewById(R.id.button)

        botonReserva.setOnClickListener {
            val direccionUrl = Uri.parse(url)
            val launchBrowser = Intent(Intent.ACTION_VIEW, direccionUrl)
            startActivity(launchBrowser)
        }

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
                url= salaD?.reserva.toString()

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        refUid.addListenerForSingleValueEvent(valueEventListener)
    }
}
