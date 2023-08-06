package com.skynoff.mymedicapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.Register.RegisterActivity
import com.skynoff.mymedicapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initObservables()
    }
    private fun initViews() {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.button.setOnClickListener {
             val email = binding.etUserEmail.text.toString().lowercase().trim()
             val pass = binding.etUserPassword.text.toString()
            viewModel.loginUser(email,pass)
        }
    }
    private fun initObservables() {

        viewModel.isUserLogged.observe(this){ isLogged ->
            if(isLogged){
                startActivity(Intent(this, RegisterActivity::class.java))
            }else{
                Toast.makeText(this, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}