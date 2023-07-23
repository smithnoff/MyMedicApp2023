package com.skynoff.mymedicapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val texto = findViewById<TextView>(R.id.text_inicio)
        db.collection("usuarios").addSnapshotListener{ response, e ->
            response?.documents?.forEach { user ->
                if(user.data?.get("email") == "cesar@gmail.com"){
                    texto.text = "${user?.data?.get("nombre")}"
                }
            }
        }

/*
        db.collection("usuarios").get()
            .addOnSuccessListener { response ->
               */
/* response.documents.forEach { user ->

                    if(user.data?.get("email") == "cesar@gmail.com")
                    {
                        texto.text = user!!.data?.get("nombre").toString()
                    }
                }*//*

                texto.text = "${response.documents
                    .firstOrNull { it.data?.get("email") == "cesar@gmail.com" }
                    ?.data?.get("nombre") ?:"No Hay usuario"}"

            }.addOnFailureListener {
                  Log.e("error firebase", it.toString())
            }
*/
    }
}