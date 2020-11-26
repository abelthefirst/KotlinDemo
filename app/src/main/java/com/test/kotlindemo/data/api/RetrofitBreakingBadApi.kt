package com.test.kotlindemo.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBreakingBadApi {

    @GET("characters")
    fun getCharacters(): Call<List<RetrofitBreakingBadCharacter>>

    @GET("characters/{id}")
    fun getCharacter(@Path("id") id: Int): Call<List<RetrofitBreakingBadCharacter>>

}