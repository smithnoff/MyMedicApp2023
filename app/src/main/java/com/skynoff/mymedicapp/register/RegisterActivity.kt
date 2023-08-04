package com.skynoff.mymedicapp.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.R
import com.skynoff.mymedicapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
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

    private fun initValidations() {
        viewModel.validateEmptys(
            binding.editTxtName.text.toString(),
            binding.editTxtNumContacto.text.toString(),
            binding.editTxtEmail.text.toString(),
            binding.editTxtPass.text.toString(),
            binding.editTxtPassConf.text.toString()
        )
        /*viewModel.validateFormatEmail(binding.editTxtEmail.text.toString())
        viewModel.validateFormatPass(binding.editTxtPass.text.toString())*/

       viewModel.validationes()
    }

    private fun initObservers() {
        viewModel.isFormatValideEmail.observe(this) { formatValide ->
            if (formatValide) {
                binding.txtInputLayoutEmail.error = "Formato de correo invalido"
            } else {
                binding.txtInputLayoutEmail.error = null
            }
        }

        viewModel.isEmptys.observe(this){isEmptys ->
            if (!isEmptys){
                viewModel.isFormatValidePass.observe(this) { formatValide ->
                    if (!formatValide) {
                        binding.txtInputLayoutPass.error =
                            "Tu contraseña debe ser alfanúmerica tener al menos 1 letra mayúscula"
                    } else {
                        binding.txtInputLayoutPass.error = null
                    }

                }
                viewModel.isCheckPass.observe(this) { checkPass ->
                    if (checkPass) {
                        binding.txtInputLayoutPassConf.error = null
                    } else {
                        binding.txtInputLayoutPassConf.error = "Contraseñas no coinciden"
                    }
                }
                viewModel.hasError.observe(this) { hasError ->
                    binding.btnRegister.isEnabled = hasError
                }
            }

        }


    }

    private fun setupTextWacther(editText: EditText, afterTextChanged:(Editable) -> Unit){
        editText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(p0: Editable?) {
                val variable = afterTextChanged(p0)
            }

        })
    }

    private fun initListener(){
        setupTextWacther(binding.editTxtName){text ->
            //initValidations()
        }
        setupTextWacther(binding.editTxtNumContacto){text ->
            initValidations()
        }
        setupTextWacther(binding.editTxtEmail){text ->
            initValidations()
            viewModel.validateFormatEmail(text.toString())
        }
        setupTextWacther(binding.editTxtPass){text ->
            initValidations()
            viewModel.validateFormatPass(text.toString())
        }
        setupTextWacther(binding.editTxtPassConf){text ->
            initValidations()
            viewModel.validateCheckPass(binding.editTxtPass.text.toString(),text.toString())
        }

    }

}