package com.test.kotlindemo.data.api

import com.google.gson.annotations.SerializedName

data class RetrofitBreakingBadCharacter(
    @SerializedName("birthday")
    val birthday: String,
    @SerializedName("char_id")
    val id: Int,
    @SerializedName("img")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("status")
    val status: String
)