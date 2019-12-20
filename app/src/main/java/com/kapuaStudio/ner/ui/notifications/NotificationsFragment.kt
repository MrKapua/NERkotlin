package com.kapuaStudio.ner.ui.notifications

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kapuaStudio.ner.R
import com.kapuaStudio.ner.data.User
import android.content.Intent as Intent

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var not_nonmbre: TextView
    private  var logUid : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
         notificationsViewModel =
             ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
         val root = inflater!!.inflate(R.layout.fragment_notifications, container, false)
         /*
         val textView: TextView = root.findViewById(R.id.text_notifications)
         notificationsViewModel.text.observe(this, Observer { textView.text = it
         })
         //return inflater!!.inflate(R.layout.fragment_notifications, container, false)
         */
       not_nonmbre = root.findViewById(R.id.not_nombre)

        val login: Intent = Intent()
        var lUid = login.getStringExtra("uid")
        auth=FirebaseAuth.getInstance()
        lUid= auth.uid.toString()
        Log.e("hola","el uid es "+ lUid )




        //var logUid : String = recuperarUid()
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("usuario").child(lUid)
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val value = dataSnapshot.getValue(User::class.java)
                    Log.d("TAG", "Value is: $value")
                    if (value != null) {
                        not_nonmbre.text=value.nombre.toString()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
         not_nonmbre.text="hola creo que he llegado aqui"
         return root
    }

    private fun recuperarUid(): String
    {
        var uid : String = ""
        return uid
    }
}