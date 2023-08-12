package com.skynoff.mymedicapp.login

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.joined.JoinedActivity
import com.skynoff.mymedicapp.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val email = findViewById<EditText>(R.id.etEmail)
        val pass = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.buttonLogin)

        btnIngresar.setOnClickListener {

             if (email.text.toString().isEmpty()){
                email.error = "Debe escribir un Email valido"
                return@setOnClickListener

            }
            else if (pass.text.toString().isEmpty()){
                pass.error = "Debe escribir una contraseña"
                return@setOnClickListener

            }else if(isValidEmail(email.text.toString().lowercase().trim()) && isValidPassword(pass.text.toString())){
            validateUser(email.text.toString().lowercase().trim(), pass.text.toString())
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
        val intent = Intent(this, JoinedActivity::class.java)
        startActivity(intent)
    }
    private fun isValidEmail(email:String): Boolean{
        val regex = Patterns.EMAIL_ADDRESS.pattern()
        val pattern = Regex(regex)
        return pattern.matches(email)
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
