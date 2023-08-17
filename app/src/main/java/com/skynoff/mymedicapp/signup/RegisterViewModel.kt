package com.skynoff.mymedicapp.signup

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skynoff.mymedicapp.login.LoginActivity

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    val isUserRegister = MutableLiveData<Boolean>()

    fun registrar(email:String,password:String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            isUserRegister.postValue(task.isSuccessful)
        }


    }
    fun mostrar (view:View){

    }
}