package com.skynoff.mymedicapp.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.joined.Joined_Activity
import com.skynoff.mymedicapp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val Email = findViewById<EditText>(R.id.etEmail)
        val Pass = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.buttonLogin)

        btnIngresar.setOnClickListener {

             if (Email.text.toString().isEmpty()){
                Email.error = "Debe escribir un Email valido"
                return@setOnClickListener

            }
            else if (Pass.text.toString().isEmpty()){
                Pass.error = "Debe escribir una contraseña"
                return@setOnClickListener

            }else if(Email.text.contains("@") && isValidPassword(Pass.text.toString())){
            validateUser(Email.text.toString().lowercase().trim(), Pass.text.toString())
        }
            else{
                Toast.makeText(this, "Clave o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }

        }

      val linkTextView = findViewById<TextView>(R.id.textRegister)
        val text = linkTextView.text.toString()

        val linkStartIndex = text.indexOf("Registrate")
        val linkEndIndex = linkStartIndex + "Registrate".length

        val spannableString = SpannableString(text)
        val clickableSpan = object : ClickableSpan(){
            override fun onClick(widget: View) {

            }
        }


spannableString.setSpan(clickableSpan, linkStartIndex, linkEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        linkTextView.text = spannableString
        linkTextView.movementMethod = android.text.method.LinkMovementMethod.getInstance()


        linkTextView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun validateUser(Email: String, pass: String) {
        val intent = Intent(this, Joined_Activity::class.java)
        startActivity(intent)
    }
    private fun isValidPassword(password: String): Boolean {
        var hasUpperCase = false
        var hasNumber = false
        var hasLowerCase = false

        for (char in password) {
            if (char.isUpperCase()) {
                hasUpperCase = true
            } else if (char.isDigit()) {
                hasNumber = true
            }
            else if (char.isLowerCase()){
                hasLowerCase = true
            }
            if (hasUpperCase && hasNumber && hasLowerCase) {
                break
            }
        }
        return hasUpperCase && hasNumber && hasLowerCase && password.length >= 8
}
}
