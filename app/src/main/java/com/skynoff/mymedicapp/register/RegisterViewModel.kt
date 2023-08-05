package com.skynoff.mymedicapp.register

import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class RegisterViewModel : ViewModel() {

    var name = MutableLiveData<String>()
    var num = MutableLiveData<String>()
    var email = MutableLiveData<String>()
    var pass1 = MutableLiveData<String>()
    var pass2 = MutableLiveData<String>()

    var hasError = MutableLiveData(false)
    var isEmptys = MutableLiveData(false)
    var isFormatValideEmail = MutableLiveData(false)
    var isFormatValidePass = MutableLiveData(false)
    var isCheckPass = MutableLiveData(false)

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
}