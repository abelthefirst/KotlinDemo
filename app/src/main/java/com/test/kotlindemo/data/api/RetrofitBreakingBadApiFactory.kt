package com.test.kotlindemo.data.api

import com.test.kotlindemo.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val TIMEOUT_TIME: Long = 10

class RetrofitBreakingBadApiFactory {

    private val retrofit: Retrofit

    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.readTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
        clientBuilder.writeTimeout(TIMEOUT_TIME, TimeUnit.SECONDS)
        clientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun build(): RetrofitBreakingBadApi = retrofit.create(RetrofitBreakingBadApi::class.java)

}