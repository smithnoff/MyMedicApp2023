package com.skynoff.mymedicapp.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.home.HomeActivity
import com.skynoff.mymedicapp.register.RegisterActivity
import com.skynoff.mymedicapp.register.RegisterViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val email = findViewById<EditText>(R.id.etEmail)
        val pass = findViewById<EditText>(R.id.etPassword)
        val btnIngresar = findViewById<Button>(R.id.buttonLogin)

        val sharedPreferences = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogged", false)) {
            email.setText( sharedPreferences.getString("email",""))
          /*  val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)*/
        } else {
            viewModel = ViewModelProvider(this)[LoginViewModel::class.java]



            btnIngresar.setOnClickListener {

                if (email.text.toString().isEmpty()) {
                    email.error = "Debe escribir un Email valido"
                    return@setOnClickListener

                } else if (pass.text.toString().isEmpty()) {
                    pass.error = "Debe escribir una contraseña"
                    return@setOnClickListener

                } else if (email.text.contains("@") && isValidPassword(pass.text.toString())) {
                    validateUser(email.text.toString().lowercase().trim(), pass.text.toString())
                } else {
                    Toast.makeText(this, "Clave o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }

            }

            val linkTextView = findViewById<TextView>(R.id.textRegister)
            val text = linkTextView.text.toString()

            val linkStartIndex = text.indexOf("Registrate")
            val linkEndIndex = linkStartIndex + "Registrate".length

            val spannableString = SpannableString(text)
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {

                }
            }


            spannableString.setSpan(
                clickableSpan,
                linkStartIndex,
                linkEndIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            linkTextView.text = spannableString
            linkTextView.movementMethod = android.text.method.LinkMovementMethod.getInstance()


            linkTextView.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun validateUser(email: String, pass: String) {
        //viewModel.validateUser(this)

        val sharedPref = getSharedPreferences("UserSession", Context.MODE_PRIVATE)
        sharedPref.edit().apply{
            putBoolean("isLogged", true)
            putString("email", email)
        }.apply()

        val intent = Intent(this, HomeActivity::class.java)
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
