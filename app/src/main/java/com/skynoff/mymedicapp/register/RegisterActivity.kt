package com.skynoff.mymedicapp.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.databinding.ActivityRegisterBinding
import com.skynoff.mymedicapp.databinding.ActivityRegisterSuccesBinding
import com.skynoff.mymedicapp.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var succesBinding: ActivityRegisterSuccesBinding
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initListener()
        initObservers()
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    private fun initObservers() {
        viewModel.name.observe(this){ name ->
            updateRegisterButtonState()
        }
        viewModel.num.observe(this){ name ->
            updateRegisterButtonState()
        }
        viewModel.email.observe(this){ name ->
            updateRegisterButtonState()

        }
        viewModel.pass1.observe(this){ name ->
            updateRegisterButtonState()
        }
        viewModel.pass2.observe(this){ name ->
            updateRegisterButtonState()
        }

        viewModel.isFormatValideEmail.observe(this){format ->
            if (!format && binding.editTxtEmail.text.toString().isNotEmpty()){
                binding.txtInputLayoutEmail.error = getString(R.string.format_email_incorrect)
            } else {
                binding.txtInputLayoutEmail.error = null
            }
        }

        viewModel.isFormatValidePass.observe(this) { formatValide ->
            if (!formatValide && binding.editTxtPass.text.toString().isNotEmpty()) {
                binding.txtInputLayoutPass.error =
                    getString(R.string.format_pass_incorrect)
            } else {
                binding.txtInputLayoutPass.error = null
            }
        }

        viewModel.isCheckPass.observe(this) { checkPass ->
            if (!checkPass && binding.editTxtPassConf.text.toString().isNotEmpty()) {
                binding.txtInputLayoutPassConf.error = getString(R.string.pass_invalide)
            } else {
                binding.txtInputLayoutPassConf.error = null
            }
        }

        viewModel.isUserRegister.observe(this){result ->
            if (result){
                Handler().postDelayed({
                    succesBinding = ActivityRegisterSuccesBinding.inflate(layoutInflater)
                    val successView = succesBinding.root
                    successView.layoutParams = binding.root.layoutParams
                    addContentView(successView,successView.layoutParams)
                    Handler().postDelayed({
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    },3000)
                },0)
            }
        }

    }

    private fun createTextWatcher( onTextChange:(String) -> Unit): TextWatcher{
       return object: TextWatcher{
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               onTextChange(p0.toString())
           }

           override fun afterTextChanged(p0: Editable?) {
           }
       }
    }

    private fun initListener(){
        binding.editTxtName.addTextChangedListener(createTextWatcher { text ->
            viewModel.name.value = text
        })
        binding.editTxtNumContacto.addTextChangedListener(createTextWatcher { text ->
            viewModel.num.value = text
        })
        binding.editTxtEmail.addTextChangedListener(createTextWatcher { text ->
            viewModel.email.value = text
        })
        binding.editTxtPass.addTextChangedListener(createTextWatcher { text ->
            viewModel.pass1.value = text
        })
        binding.editTxtPassConf.addTextChangedListener(createTextWatcher { text ->
            viewModel.pass2.value = text
        })

        binding.btnRegister.setOnClickListener {
            viewModel.registerUser(binding.editTxtName.text.toString(),
                binding.editTxtNumContacto.text.toString(),
                binding.editTxtEmail.text.toString(),
                binding.editTxtPassConf.text.toString())
        }
    }

    private fun updateRegisterButtonState(){
        binding.btnRegister.isEnabled = viewModel.isDataValid()
    }
}