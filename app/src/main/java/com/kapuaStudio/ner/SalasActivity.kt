package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kapuaStudio.ner.data.Salas
import kotlinx.android.synthetic.main.lo_empresas.view.*
import kotlinx.android.synthetic.main.lo_salas.view.*

class SalasActivity : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDataBase : DatabaseReference
    lateinit var nombreEmpresaSeleccionada : String
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salas_empresas)
        nombreEmpresaSeleccionada = intent.getStringExtra("nomEmpresa")
        mDataBase = FirebaseDatabase.getInstance().getReference("Salas")
        mRecyclerView = findViewById(R.id.listsala_empresalist)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
        rellenarSalas()
    }
    private fun rellenarSalas()
    {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Salas, SalasViewHolder>
            (Salas::class.java, R.layout.lo_salas, SalasViewHolder::class.java, mDataBase.orderByChild("empresa").equalTo(nombreEmpresaSeleccionada))
        {
            override fun populateViewHolder(viewHolder: SalasViewHolder?, model: Salas?, position: Int)
            {
                viewHolder!!.itemView.tv_nom_sala.setText(model!!.nombre)
                viewHolder!!.itemView.tv_jugadores_sala.setText(model!!.jugadores)
                viewHolder!!.itemView.tv_tematica_sala.setText(model!!.tematica)
                viewHolder!!.itemView.salaUID.setText((model!!.uid))
                viewHolder!!.itemView.tv_precio_sala.setText(model!!.precio)
                //print(model)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    class SalasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init
        {
            itemView.setOnClickListener{
                val intent = Intent ( itemView.context, SalaDetalle::class.java)
                intent.putExtra("nomSala",itemView.salaUID.text)
                itemView.context.startActivity(intent)
            }
        }
    }
}
