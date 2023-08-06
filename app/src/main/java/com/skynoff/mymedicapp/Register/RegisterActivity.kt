package com.skynoff.mymedicapp.Register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.databinding.ActivityLoginBinding
import com.skynoff.mymedicapp.databinding.ActivityRegisterBinding
import com.skynoff.mymedicapp.login.LoginActivity
import com.skynoff.mymedicapp.login.LoginViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alertDialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.pop_up)
        }.create()
        binding.btnregistro.S


       /* binding.btnregistro.isEnabled(false)*/
        initViews()
        initObservables()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding.btnregistro.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val phone = binding.etUserPhone.text.toString()
            val email = binding.etUserEmail.text.toString().lowercase().trim()
            val pass = binding.etUserPassword.text.toString()
            viewModel.registrar(email, pass)
        }
    }
    private fun validarcampos():Boolean{
        var esvalido = true
        val name = binding.etUserName.text.toString()
        val phone=binding.etUserPhone.text.toString()
        val email =binding.etUserEmail.text.toString()
        val pass = binding.etUserPassword.text.toString()
        val passc = binding.etConfirPass.text.toString()

        if (name.isEmpty() or phone.isEmpty() or email.isEmpty() or pass.isEmpty() or passc.isEmpty()){
            Toast.makeText(this, "Todos los campos son obligatorios para su registro", Toast.LENGTH_SHORT).show()
         else {
             binding.btnregistro.isEnabled = true
            }
        }

            binding.btnregistro.isEnabled = true
            }

    private fun initObservables() {

            viewModel.isUserRegister.observe(this){ isRegister ->
                if(isRegister){
                    startActivity(Intent(this, LoginActivity::class.java))
                }else{
                    Toast.makeText(this, "Registro Fallido", Toast.LENGTH_SHORT).show()
                }
            }
        }
   /*} binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnregistro.setOnClickListener {
            registrarUsuarioFirebase(
                binding.etUserEmail.text.toString().lowercase().trim(),
                binding.etUserPassword.text.toString()
            )
        }*/
    }

   /*     private fun registrar(name:String,phone:String,email: String, password: String) {
            auth.createUserWithEmailAndPassword(name,phone,email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Usuario Registrado", Toast.LENGTH_SHORT,).show()
                        startActivity(Intent(this,LoginActivity::class.java))

                    } else {

                        Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    }
                }
        }
    }
*/