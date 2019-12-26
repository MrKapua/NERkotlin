package com.kapuaStudio.ner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.kapuaStudio.ner.data.Salas
import kotlinx.android.synthetic.main.lo_salas.view.*

class SalasActivity : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDataBase : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salas_empresas)
        mDataBase = FirebaseDatabase.getInstance().getReference("Salas")
        mRecyclerView = findViewById(R.id.listsala_empresalist)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
        rellenarSalas()
    }
    private fun rellenarSalas()
    {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Salas, SalasViewHolder>
            (Salas::class.java, R.layout.lo_salas, SalasViewHolder::class.java, mDataBase)
        {
            override fun populateViewHolder(viewHolder: SalasViewHolder?, model: Salas?, position: Int)
            {
                viewHolder!!.itemView.tv_nom_sala.setText(model!!.nombre)
                viewHolder!!.itemView.tv_tematica_sala.setText(model!!.tematica)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    class SalasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init {}
    }
}
