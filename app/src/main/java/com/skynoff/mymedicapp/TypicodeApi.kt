package com.skynoff.mymedicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TypicodeApi {

    @GET("/users")
    fun getUsers():Call<List<User>>
    @GET("/posts/{id}")
    fun getPostDetail(@Path("id")postid:String):Call<List<User>>
    @GET("/users/{id}")
    fun getUser(@Path("id")userid:String):Call<User>
}
