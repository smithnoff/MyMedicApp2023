package com.skynoff.mymedicapp.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.databinding.ActivityRegisterBinding
import com.skynoff.mymedicapp.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservables()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        binding.etUserName.doOnTextChanged { text, start, before, count ->
            binding.btnregistro.isEnabled = validarcampos()
         }
        binding.etUserPassword.doOnTextChanged { text, start, before, count ->
            binding.btnregistro.isEnabled = validarcampos()
         }
        binding.etUserEmail.doOnTextChanged { text, start, before, count ->
            binding.btnregistro.isEnabled = validarcampos()
         }
        binding.etConfirPass.doOnTextChanged { text, start, before, count ->
            binding.btnregistro.isEnabled = validarcampos()
         }
        binding.etUserPhone.doOnTextChanged { text, start, before, count ->
            binding.btnregistro.isEnabled = validarcampos()
         }

        binding.btnregistro.setOnClickListener {
            val name = binding.etUserName.text.toString()
            val phone = binding.etUserPhone.text.toString()
            val email = binding.etUserEmail.text.toString().lowercase().trim()
            val pass = binding.etUserPassword.text.toString()
            viewModel.registrar(email, pass)
        }

        binding.btBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun validarcampos(): Boolean = with(binding){

        val name = etUserName.text.toString()
        val phone = etUserPhone.text.toString()
        val email = etUserEmail.text.toString()
        val pass = etUserPassword.text.toString()
        val passc = etConfirPass.text.toString()


        return name.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty() && passc.isNotEmpty()
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
    }

