package com.skynoff.mymedicapp.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth
    val isUserLogged = MutableLiveData<Boolean>()

    fun loginUser(email:String, password:String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                isUserLogged.postValue(task.isSuccessful)
        }
    }
}