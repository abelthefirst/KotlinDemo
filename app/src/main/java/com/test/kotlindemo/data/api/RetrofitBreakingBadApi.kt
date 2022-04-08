package com.test.kotlindemo.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBreakingBadApi {

    @GET("characters")
    suspend fun getCharacters(): List<RetrofitBreakingBadCharacter>

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: Int): List<RetrofitBreakingBadCharacter>

}