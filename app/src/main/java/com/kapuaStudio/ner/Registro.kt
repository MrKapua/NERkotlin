package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kapuaStudio.ner.data.Users


class Registro : AppCompatActivity()
{
    //preparamos variables para rellenar más adelante
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        //tenemos la instancia de AUTH de firebase
        auth=FirebaseAuth.getInstance()
        //Esto nos va a hacer falta
        //val spinnerAdapter = ArrayAdapter.createFromResource(this, provincias,R.layout.support_simple_spinner_dropdown_item)
        //spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //si pulsamos sobre registro
        val btnRegistro = findViewById<View>(R.id.btnRegistro) as Button
        btnRegistro.setOnClickListener{
            registrar()
        }
    }
    private fun registrar()
    {
        //Localizamos los EditText de la activity
        val txtmail = findViewById<EditText>(R.id.reg_email)
        val txtpass = findViewById<EditText>(R.id.reg_pass)
        val txtnom = findViewById<EditText>(R.id.reg_nombre)
        var txtape = findViewById<EditText>(R.id.reg_apellido)
        //val txtprov = regProv.selectedItem.toString()//procisional
        val txtprov = "Cáceres"//por ahora somos todos de Cáceres
        //guardamos el contenido dentro de variables en el momento de pulsar el botón
        var mail = txtmail.text.toString()
        var pass = txtpass.text.toString()
        var nomb = txtnom.text.toString()
        var ape = txtape.text.toString()
        //comprobamos si ambos campos estan rellenos
        if(!mail.isEmpty() && !pass.isEmpty() && !nomb.isEmpty() && !ape.isEmpty())
        {
            auth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener { task ->
                if (task.isSuccessful)// si es correcta la comprobación volvemos a la activity anterior pasanto la direción de mail por el intent
                {
                    val user = auth.currentUser
                    val usuario = Users("jose")
                    registrarNombre(user?.uid.toString(), nomb,ape,txtprov)
                    val intent:Intent= Intent(this, Login::class.java)
                    intent.putExtra("dirEmail", mail)//pasamos el correo nuevo a la pantalla de Login
                    startActivity(intent)//lanzamos el intent
                    finish() // y cerramos este
                }
                else // si no... pues damos un mensaje de alerta
                {
                    Toast.makeText(this,"algo no va"+ (task.exception?.message ?: String() ),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun registrarNombre(uid :String, nombre: String, apellido:String, provincia : String)
    {
        //vamos a crear el registro nuevo
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuario")
        val usuario = Users(nombre, apellido,provincia,uid)
        myRef.child(uid).setValue(usuario)
    }
}
