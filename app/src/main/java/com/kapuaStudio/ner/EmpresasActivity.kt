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
import com.kapuaStudio.ner.data.Empresas
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lo_empresas.*
import kotlinx.android.synthetic.main.lo_empresas.view.*

class EmpresasActivity : AppCompatActivity()
{
    lateinit var mRecyclerView : RecyclerView
    lateinit var mDataBase : DatabaseReference
    lateinit var uid : String

    private var lat:Long= 0L
    private var long:Long= 0L
    //lateinit var filtro : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresas)
        mDataBase = FirebaseDatabase.getInstance().getReference("Empresa")
        var valor = intent.getIntExtra("tipo", 0)
        val nombre = intent.getStringExtra("nombre")

        val distancia =intent.getIntExtra("distancia", 50)
        val lo0=intent.getLongExtra("lat", lat)
        val la0 =intent.getLongExtra("long", long)
        if(nombre.equals("todas"))
        {
            valor=3
        }
        mRecyclerView = findViewById(R.id.listempresalist)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
        when(valor){
            1 -> rellenarEmpresasPorCiudad(nombre)
            2 -> rellenarEmpresasPorProvincia(nombre)
            3 -> rellenarEmpresas()
            4 -> rellenarPorDistancia(la0,lo0,distancia.toDouble())
        }
        //rellenarEmpresas()
    }
    private fun rellenarEmpresasPorCiudad(nombre:String)
    {
        //var filtro = mDataBase.orderByChild("ciudad").equalTo(nombre)
        var filtro = mDataBase.equalTo("ciudad",nombre)
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Empresas, EmpresaViewHolder>
            (Empresas::class.java, R.layout.lo_empresas, EmpresaViewHolder::class.java, mDataBase.orderByChild("ciudad").equalTo(nombre))
        {
            override fun populateViewHolder(viewHolder: EmpresaViewHolder?, model: Empresas?, position: Int)
            {
                viewHolder!!.itemView.nom_empresa.setText(model!!.nombre)
                viewHolder!!.itemView.ciudad_empresa.setText(model!!.ciudad)
                viewHolder!!.itemView.provincia_empresa.setText(model!!.provincia)
                viewHolder!!.itemView.num_telf_empresa.setText("Teléfono: "+ model!!.telf)
                viewHolder!!.itemView.num_uid.setText(model!!.uid)
                Picasso.get()
                    .load(model!!.foto)
                    .into(viewHolder!!.itemView.logoEmpresa)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
    private fun rellenarEmpresasPorProvincia(nombre:String)
    {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Empresas, EmpresaViewHolder>
            (Empresas::class.java, R.layout.lo_empresas, EmpresaViewHolder::class.java, mDataBase.orderByChild("provincia").equalTo(nombre))
        {
            override fun populateViewHolder(viewHolder: EmpresaViewHolder?, model: Empresas?, position: Int)
            {
                viewHolder!!.itemView.nom_empresa.setText(model!!.nombre)
                viewHolder!!.itemView.ciudad_empresa.setText(model!!.ciudad)
                viewHolder!!.itemView.provincia_empresa.setText(model!!.provincia)
                viewHolder!!.itemView.num_telf_empresa.setText(model!!.telf)
                viewHolder!!.itemView.num_uid.setText(model!!.uid)
                Picasso.get()
                    .load(model!!.foto)
                    .into(viewHolder!!.itemView.logoEmpresa)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }
    private fun rellenarEmpresas()
    {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Empresas, EmpresaViewHolder>
            (Empresas::class.java, R.layout.lo_empresas, EmpresaViewHolder::class.java, mDataBase.orderByChild("nombre"))
        {
            override fun populateViewHolder(viewHolder: EmpresaViewHolder?, model: Empresas?, position: Int)
            {
                viewHolder!!.itemView.nom_empresa.setText(model!!.nombre)
                viewHolder!!.itemView.ciudad_empresa.setText(model!!.ciudad)
                viewHolder!!.itemView.provincia_empresa.setText(model!!.provincia)
                viewHolder!!.itemView.num_telf_empresa.setText(model!!.telf)
                viewHolder!!.itemView.num_uid.setText(model!!.uid)
                Picasso.get()
                    .load(model!!.foto)
                    .into(viewHolder!!.itemView.logoEmpresa)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

    class EmpresaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        init {
            itemView.setOnClickListener{
                Log.e("hola", "he tocado algo "+ itemView.nom_empresa.text )
                //val intent = Intent ( itemView.context, SalasActivity::class.java)
                val intent = Intent ( itemView.context, EmpresaDetalle::class.java)
                intent.putExtra("nomEmpresa",itemView.num_uid.text)
                itemView.context.startActivity(intent)
            }
        }
    }
    private fun rellenarPorDistancia(lat:Long, long: Long, double: Double)
    {
        var FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<Empresas, EmpresaViewHolder>
            (Empresas::class.java, R.layout.lo_empresas, EmpresaViewHolder::class.java, mDataBase.orderByChild("nombre"))
        {
            override fun populateViewHolder(viewHolder: EmpresaViewHolder?, model: Empresas?, position: Int)
            {

                viewHolder!!.itemView.nom_empresa.setText(model!!.nombre)
                viewHolder!!.itemView.ciudad_empresa.setText(model!!.ciudad)
                viewHolder!!.itemView.provincia_empresa.setText(model!!.provincia)
                viewHolder!!.itemView.num_telf_empresa.setText(model!!.telf)
                viewHolder!!.itemView.num_uid.setText(model!!.uid)
                Picasso.get()
                    .load(model!!.foto)
                    .into(viewHolder!!.itemView.logoEmpresa)
            }
        }
        mRecyclerView.adapter = FirebaseRecyclerAdapter
    }

}
