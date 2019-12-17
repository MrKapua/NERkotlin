package com.kapuaStudio.ner

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.kapuaStudio.ner.data.User
import java.util.prefs.PreferencesFactory


class Login : AppCompatActivity()
{
    private lateinit var mAuth: FirebaseAuth
    private lateinit var usuarioRef : FirebaseDatabase
    private lateinit var logUid : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //inicializamos Firebase Auth
        mAuth = FirebaseAuth.getInstance()
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
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("usuario")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(User::class.java)
                    Log.d("TAG", "Value is: $value")
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
            Toast.makeText(this, "El registro ha concluido correctamente, ya puedes iniciar sesión",Toast.LENGTH_SHORT).show()
        }
        btnMainMenu.setOnClickListener ( View.OnClickListener {
                view -> login()
        })
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
            mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this)
            {
                task ->
                if(task.isSuccessful)//Si el login es correcto
                {
                    Toast.makeText(this, "Todo correcto",Toast.LENGTH_SHORT).show()
                    val i :Intent = Intent(this,MainMenu::class.java)
                    i.putExtra("uid",mAuth.uid.toString())
                    //guardarUid(logUid)
                    startActivity(i)
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
    private fun guardarUid(uid : String)
    {

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val editPref = prefs.edit()
        editPref.putString(logUid, uid)
        editPref.apply()

    }
}