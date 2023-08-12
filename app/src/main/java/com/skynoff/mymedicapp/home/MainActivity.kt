package com.skynoff.mymedicapp.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.skynoff.mymedicapp.R

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val texto = findViewById<TextView>(R.id.text_inicio)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getUserName()

        viewModel.userName.observe(this) { user ->
            texto.text = user
        }
        viewModel.cambiarNombre()
    }
}