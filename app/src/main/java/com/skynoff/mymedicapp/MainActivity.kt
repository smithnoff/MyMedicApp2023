package com.skynoff.mymedicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listarUsuario()

    }
         fun listarUsuario() {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

            val api = retrofit.create<TypicodeApi>()

            api.getPost().enqueue(object: Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful){
                        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

                        recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                        val mediclist = response.body()?.map {it.title}
                        val adapter = MedicAdapter(mediclist?: listOf())
                        recycler.adapter = adapter
                    }else{
                        Toast.makeText(this@MainActivity,"Error ${response.code()}", Toast.LENGTH_SHORT).show()

                }}
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {

                }
            })
            }

    }

