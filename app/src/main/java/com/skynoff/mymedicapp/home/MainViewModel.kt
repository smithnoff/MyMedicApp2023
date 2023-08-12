package com.skynoff.mymedicapp.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel()  {

    private val db = FirebaseFirestore.getInstance()
    val userName = MutableLiveData<String>()

    fun getUserName(){
       /* db.collection("usuarios").addSnapshotListener { response, e ->
            response?.documents?.forEach { user ->
                if (user.data?.get("email") == "cesar@gmail.com") {
                    userName.postValue(user?.data?.get("nombre")?.toString())
                }
            }
        }*/


                db.collection("usuarios").get()
                    .addOnSuccessListener { response ->

         response.documents.forEach { user ->

                            if(user.data?.get("email") == "cesar@gmail.com")
                            {
                                userName.postValue(user?.data?.get("nombre")?.toString())
                            }
                        }

            }.addOnFailureListener {
            }


    }

    fun cambiarNombre(){
        userName.postValue( "Cesin Galindo")
    }

}