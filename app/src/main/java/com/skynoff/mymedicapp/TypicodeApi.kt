package com.skynoff.mymedicapp

import retrofit2.Call
import retrofit2.http.GET

interface TypicodeApi {

    @GET("/posts")
    fun getPost():Call<List<Post>>

    @GET("/Users")
    fun getUsers():Call<List<User>>
}