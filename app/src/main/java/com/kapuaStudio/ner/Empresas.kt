package com.kapuaStudio.ner

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kapuaStudio.ner.data.Empresas
import kotlinx.android.synthetic.main.lo_empresas.view.*

class Empresas : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresas)
        mDataBase = FirebaseDatabase.getInstance().getReference("Empresa")
        mRecyclerView = findViewById(R.id.listempresalist)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))



        logRecyvlerView()
    }

    private fun logRecyvlerView()
    {
        var  FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Empresas, EmpresaViewHolder>(Empresas::class.java, R.layout.lo_empresas, EmpresaViewHolder::class.java, mDataBase)
        {
            override fun populateViewHolder(viewHolder: EmpresaViewHolder?, model: Empresas?, position: Int)
            {
                viewHolder!!.itemView.nom_empresa.setText(model!!.nombre)
                viewHolder!!.itemView.ciudad_empresa.setText(model!!.ciudad)
                viewHolder!!.itemView.provincia_empresa.setText(model!!.provincia)
                viewHolder!!.itemView.num_telf_empresa.setText(model!!.telf)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
    class EmpresaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init {
            itemView.setOnClickListener{
                Log.e("hola", "he tocado algo "+ itemView.nom_empresa.text )
                val intent = Intent ( itemView.context, Usuario::class.java)
                itemView.context.startActivity(intent)


                }

        }


    }
}
