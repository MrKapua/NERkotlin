package com.kapuaStudio.ner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity()
{
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //inicializamos Firebase Auth
        auth = FirebaseAuth.getInstance()
        var tv_email = findViewById<TextView>(R.id.txt_mail)
        var btnMainMenu = findViewById<Button>(R.id.btn_log)
        var btnRegistrar=findViewById<Button>(R.id.registrar_log)
        /*
            Se busca una cadena de texto procedente de un intent, en caso de ser correcto significa
            que proviene del activity de registro, por lo cual se autorellena el texto de mail de
            usuario y aparece un Toast que indica que la cuenta se ha generado correctamente
         */
        val registroIntent=intent
        var mail = registroIntent.getStringExtra("dirEmail")
        if(mail!=null)
        {
            tv_email.setText(""+mail)
            Toast.makeText(this, "El registro ha concluido correctamente, ya puedes iniciar sesión",Toast.LENGTH_SHORT).show()
        }
        //Si se pulsa sobre el boton de Login esta función nos mandará a la comprobación de Firebase
        // para conceder el acceso
        btnMainMenu.setOnClickListener ( View.OnClickListener {
                view -> login()
        })
        //Si pulsamos sobre el boton de registrar esta función nos mandará directamente a la activity
        // de @registro.kt
        btnRegistrar.setOnClickListener{
                startActivity(Intent(this,Registro::class.java))
            finish()
            }
    }
    private fun login()
    {
        //localizamos los EditText
        var tv_email = findViewById<TextView>(R.id.txt_mail)
        var tv_pass = findViewById<TextView>(R.id.txt_pass)
        //leemos el contenido de los dos textViews necesarios para el Login
        var email=tv_email.text.toString()
        var pass = tv_pass.text.toString()
        //Comprobamos si los campos de mail y de pass están completos
        if( !email.isEmpty() && !pass.isEmpty())
        {
            auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this)
            {
                task ->
                if(task.isSuccessful)//Si el login es correcto
                {
                    Toast.makeText(this, "Todo correcto",Toast.LENGTH_SHORT).show()
                    //pasamos comprobar si tiene datos dentro de firebase
                    comprobarUID()
                }
                else// si algo sale mal...
                {
                    Toast.makeText(this, "Autenticación incorrecta",Toast.LENGTH_SHORT).show()
                }
            }
        }
        else
        {
            //Si falta algun dato de los necesarios se mostrará el siguiente mensaje de error
            Toast.makeText(this,"rellena todos los campos", Toast.LENGTH_LONG).show()
        }
    }
    fun comprobarUID()
    {
        // obtenemos el usuario con el que se ha autenticado y obtenemos si UID, identificador unico
        // dentro de la base de datos
        var user = auth.currentUser
        Toast.makeText(this, "uid: "+user?.uid, Toast.LENGTH_LONG).show()
        // la instancia a la base de datos
        var fbDase = FirebaseDatabase.getInstance()
        // la referencia a la instancia anterior
        var referencia = fbDase.getReference(user!!.uid)
        //preparamos el intent para su posterior uso
        var i:Intent;
        // si no tenemos datos dentro de el campo nombre pasaremos al intent de datosPlus
        if(referencia.child("nombre").toString().isEmpty())
        {
           i = Intent(this, DatosPlus::class.java)
        }
        // si no, entraremos en el menu principal con el usuario logueado
        else
        {
            i = Intent(this, MainMenu::class.java)
        }
        //nos vamos a otra activity, vamos
        startActivity(i)
    }
}