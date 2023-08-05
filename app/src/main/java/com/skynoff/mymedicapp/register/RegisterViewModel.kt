package com.skynoff.mymedicapp.register

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    var name = MutableLiveData<String>()
    var num = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var pass1 = MutableLiveData<String>()
    var pass2 = MutableLiveData<String>()

    var isFormatValideEmail = MutableLiveData(false)
    var isFormatValidePass = MutableLiveData(false)
    var isCheckPass = MutableLiveData(false)

    private val auth: FirebaseAuth = Firebase.auth
    val isUserRegister = MutableLiveData<Boolean>()

    fun isDataValid():Boolean{
        var valideEmptys =  validateEmptys(name.value.toString(),num.value.toString(),email.value.toString(),pass1.value.toString(),pass2.value.toString())
        var valideFormtEmail = validateFormatEmail(email.value.toString())
        var valideFormtPass = validateFormatPass(pass1.value.toString())
        var valideChekPass = validateCheckPass(pass1.value.toString(),pass2.value.toString())
        return valideEmptys && valideFormtEmail && valideFormtPass&& valideChekPass
    }


    private fun validateEmptys(name: String, number: String, email: String, password: String, passwordCheck:String): Boolean {
      return  name.isNotEmpty() && number.isNotEmpty()&& email.isNotEmpty() && password.isNotEmpty()&&passwordCheck.isNotEmpty()
    }

    fun validateFormatEmail(email: String): Boolean {
        return if (PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            isFormatValideEmail.postValue(true)
            true
        }else{
            isFormatValideEmail.postValue(false)
            false
        }
    }

    fun validateFormatPass(password: String): Boolean {
        val formatPass = Pattern.compile("^(?=.*[A-Z]).{8,}\$")
       return if (formatPass.matcher(password).matches()) {
           isFormatValidePass.postValue(true)
           true
       }else{
           isFormatValidePass.postValue(false)
           false
       }
    }

    fun validateCheckPass(password1: String, password2: String):Boolean {
      return if (password1 == password2) {
          isCheckPass.postValue(true)
          true
      }else{
          isCheckPass.postValue(false)
          false
      }
    }
    fun registerUser(name: String,number: String,email: String,password: String){
       auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {task ->
                if (task.isSuccessful){
                    isUserRegister.postValue(true)
                }
            }
    }
}