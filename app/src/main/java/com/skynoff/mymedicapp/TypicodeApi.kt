package com.skynoff.mymedicapp

import retrofit2.Call
import retrofit2.http.GET

interface TypicodeApi {

    @GET("/users")
    fun getUsers():Call<List<User>>
}