package com.skynoff.mymedicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class LoginActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()

        val etEmail = findViewById<TextInputEditText>(R.id.etUserEmail)
        val etpass = findViewById<TextInputEditText>(R.id.etUserPass)

        val btIngresar = findViewById<Button>(R.id.button)
        btIngresar.setOnClickListener {
            validarUsuario(etEmail.text.toString().lowercase(), etpass.text.toString())
        }

    }

    private fun validarUsuario(email: String, password: String) {

        val api = retrofit.create<TypicodeApi>()

        api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val usuarios = response.body() ?: emptyList()
                    val userEncontrado = usuarios.any { user ->
                        user.email.lowercase() == email && user.id == password
                    }
                    if (userEncontrado) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        if (usuarios.any { user -> user.email.lowercase() == email }) {
                            Toast.makeText(
                                this@LoginActivity,
                                R.string.clave_incorrecta,
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                R.string.correo_no_encontrado,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }
        })
    }
}