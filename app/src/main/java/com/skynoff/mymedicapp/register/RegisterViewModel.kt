package com.skynoff.mymedicapp.register

import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    var hasError = MutableLiveData(false)
    var isEmptys = MutableLiveData(true)
    var isFormatValideEmail = MutableLiveData(false)
    var isFormatValidePass = MutableLiveData(false)
    var isCheckPass = MutableLiveData(false)


    fun validateEmptys(name: String, number: String, email: String, password: String,passwordCheck:String) {
        if (name.isEmpty() || number.isEmpty()|| email.isEmpty() || password.isEmpty()||passwordCheck.isEmpty()) {
            isEmptys.postValue(true)
        } else {
            isEmptys.postValue(false)
        }
    }

    fun validateFormatEmail(email: String) {
        if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            isFormatValideEmail.postValue(true)
        }else{
            isFormatValideEmail.postValue(false)
        }

    }

    fun validateFormatPass(password: String) {
        val formatPass = Pattern.compile("^(?=.*[A-Z]).{8,}\$")
        if(formatPass.matcher(password).matches()){
            isFormatValidePass.postValue(true)

        }else{
            isFormatValidePass.postValue(false)
        }
    }

    fun validateCheckPass(password1: String, password2: String) {
        if (password1 == password2) {
            isCheckPass.postValue(true)
        } else {
            isCheckPass.postValue(false)
        }
    }

    fun validationes(){
        if (isEmptys.value == false||isFormatValideEmail.value==true||isFormatValidePass.value ==true||isCheckPass.value==true){
            hasError.postValue(true)
        }else{
            hasError.postValue(false)
        }
    }
}