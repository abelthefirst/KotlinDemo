package com.test.kotlindemo.data.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitBreakingBadApi {

    @GET("characters")
    fun getCharacters(): Flowable<List<RetrofitBreakingBadCharacter>>

    @GET("characters/{id}")
    fun getCharacter(@Path("id") id: Int): Flowable<List<RetrofitBreakingBadCharacter>>

}