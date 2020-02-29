package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.data.EmpresaFullData
import kotlinx.android.synthetic.main.activity_empresa_detalle.*




import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




class EmpresaDetalle : AppCompatActivity() ,OnMapReadyCallback
{

    private var googleMap:GoogleMap?=null


    private lateinit var auth: FirebaseAuth
    private var lati : Double = 0.0
    private var longi : Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empresa_detalle)



        var btnSalas : Button= findViewById(R.id.btn_lista_salas_empresa)

        val nombreEmpresaSeleccionada:String = intent.getStringExtra("nomEmpresa")

        var nombre_empresa : TextView = findViewById(R.id.lbl_nom_empresa_detalle)
        nombre_empresa.setText(nombreEmpresaSeleccionada)
        auth=FirebaseAuth.getInstance()


        rellenarDatos(nombreEmpresaSeleccionada)


        btnSalas.setOnClickListener{
            val intent = Intent (this, SalasActivity::class.java)
            intent.putExtra("nomEmpresa", nombreEmpresaSeleccionada)
            startActivity(intent)
        }
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    private fun rellenarDatos(nombreEmpresaSeleccionada:String)
    {
        val refMaestra = FirebaseDatabase.getInstance().reference
        val refUid =  refMaestra.child("Empresa").child(nombreEmpresaSeleccionada)

        val valueEventListener = object  : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val empresad = dataSnapshot.getValue(EmpresaFullData::class.java)
                //Log.e("hola", "hola soy...." + empresad!!.nombre)
                lbl_ciudad_direccion_detalle.setText(empresad?.direccion)
                lbl_nom_empresa_detalle.setText(empresad?.nombre)
                lbl_ciudad_empresa_detalle.setText(empresad?.ciudad)

                lbl_ciudad_provincia_detalle.setText(empresad?.provincia)
                lbl_ciudad_telf_detalle.setText(empresad?.telf)

                lati=empresad?.latitud!!.toDouble()
                longi=empresad?.longitud!!.toDouble()
                //geoLoc=LatLng(empresad?.longitud!!.toDouble(),(empresad?.latitud!!.toDouble()))


            }
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        refUid.addListenerForSingleValueEvent(valueEventListener)
    }

    override fun onMapReady(p0: GoogleMap?) {
        googleMap=p0
        Log.e("hola", "estoy aqui?")
        //Adding markers to map
        var la0 :Double = lati
        var lo0 : Double = longi
        Log.e("hola", la0.toString())
        Log.e("hola", lo0.toString())
        //var latLng=LatLng(28.6139,77.2090)
        var latLng = LatLng(la0,lo0)


        if(latLng.latitude==0.0)
        {
            try {
                //Ponemos a "Dormir" el programa durante los ms que queremos
                Log.e("hola", lati.toString())
                //Thread.sleep((5 * 1000).toLong())
                latLng=LatLng(lati,longi)
                Log.e("hola", lati.toString())
            } catch (e: Exception) {
                println(e)
            }
            Log.e("hola", latLng.toString())
        }
        //val latLng=geoLoc
        val markerOptions:MarkerOptions=MarkerOptions().position(latLng).title(lbl_nom_empresa_detalle!!.text.toString())

        // moving camera and zoom map

        val zoomLevel = 16.0f //This goes up to 21


        googleMap.let {
            it!!.addMarker(markerOptions)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
    }
}
