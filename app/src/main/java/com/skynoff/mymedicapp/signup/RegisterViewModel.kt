package com.skynoff.mymedicapp.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth
    val isUserRegister = MutableLiveData<Boolean>()

    fun registrar(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            isUserRegister.postValue(task.isSuccessful)
        }

    }



}