package com.test.kotlindemo.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitBreakingBadApi {

    @GET("characters")
    fun getCharacters(@Query("offset") offset: Int, @Query("limit") pageSize: Int): Call<List<RetrofitBreakingBadCharacter>>

    @GET("characters/{id}")
    fun getCharacter(@Path("id") id: Int): Call<List<RetrofitBreakingBadCharacter>>

}
