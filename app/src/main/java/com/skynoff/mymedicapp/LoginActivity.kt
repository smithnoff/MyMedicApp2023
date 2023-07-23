package com.skynoff.mymedicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class LoginActivity : AppCompatActivity() {

    lateinit var retrofit : Retrofit
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //initRetrofit()
        auth = Firebase.auth


        val etEmail = findViewById<TextInputEditText>(R.id.etUserEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etUserPassword)
        val btIngresar = findViewById<Button>(R.id.button)

        btIngresar.setOnClickListener {
            validarUsuarioFirebase(etEmail.text.toString().lowercase().trim(),etPassword.text.toString())
            //validarUsuario(etEmail.text.toString().lowercase())
        }

    }

    private fun validarUsuarioFirebase(email:String, password:String){
       auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
           if(task.isSuccessful){
              startActivity(Intent(this,MainActivity::class.java))
           }else{
               Toast.makeText(this, "Usuario o contraseña invalida", Toast.LENGTH_SHORT).show()
           }
       }
    }

    private fun initRetrofit(){
        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
    }

    private fun validarUsuario(email: String) {

        val api = retrofit.create<TypicodeApi>()

        api.getUsers().enqueue(object: Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    val usuariosRegistados = response.body()?.map { it.email.lowercase() }
                    usuariosRegistados?.let {
                        if(it.contains(email))
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                        else
                            Toast.makeText(this@LoginActivity, "El usuario no esta registrado", Toast.LENGTH_SHORT).show()

                    }

                }else{
                    Toast.makeText(this@LoginActivity, "Error ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {

            }
        })
    }
}