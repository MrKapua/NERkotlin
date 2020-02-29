package com.kapuaStudio.ner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth


class pruebas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pruebas)
        var auth= FirebaseAuth.getInstance()

        var imView : ImageView = findViewById(R.id.imgPicasso)



        //val storage = FirebaseStorage.getInstance()
        //val gsReference : StorageReference = storage.getReferenceFromUrl("//gs://neroom-57764.appspot.com/sala/EMP001.jpg")

    }
}
