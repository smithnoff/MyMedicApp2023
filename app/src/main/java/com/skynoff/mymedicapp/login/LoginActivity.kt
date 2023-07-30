package com.skynoff.mymedicapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.home.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    lateinit var etEmail: TextInputEditText
    private lateinit var etPassword: TextInputEditText
    lateinit var btIngresar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViews()
        initObservables()
    }
    private fun initViews() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        etEmail = findViewById(R.id.etUserEmail)
        etPassword = findViewById(R.id.etUserPassword)
        btIngresar = findViewById(R.id.button)

        btIngresar.setOnClickListener {
            val email = etEmail.text.toString().lowercase().trim()
            val pass = etPassword.text.toString()
            viewModel.loginUser(email,pass)
        }
    }
    private fun initObservables() {

        viewModel.isUserLogged.observe(this){ isLogged ->
            if(isLogged){
                startActivity(Intent(this,MainActivity::class.java))
            }else{
                Toast.makeText(this, "usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }
    }

}