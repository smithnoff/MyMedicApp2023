package com.skynoff.mymedicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.skynoff.mymedicapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var retrofit: Retrofit
    private lateinit var adapter: MedicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
        initUI()

    }

    private fun initUI(){
        binding.rvListar.setHasFixedSize(true)
        listar_recycler()
    }

    private fun listar_recycler() {
        binding.progressBar.isVisible = true
        val api = retrofit.create<TypicodeApi>()
        api.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, myResponse: Response<List<User>>) {
                if (myResponse.isSuccessful) {
                    val response = myResponse.body()
                    if (response!=null){
                        binding.rvListar.layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = MedicAdapter(response)
                        binding.rvListar.adapter = adapter
                        binding.progressBar.isVisible = false

                    }

                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error ${myResponse.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}