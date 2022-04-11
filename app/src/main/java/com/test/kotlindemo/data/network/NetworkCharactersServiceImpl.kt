package com.test.kotlindemo.data.network

import android.content.res.Resources
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.service.NetworkCharactersService
import com.test.kotlindemo.data.api.RetrofitBreakingBadApi
import java.lang.Exception

class NetworkCharactersServiceImpl(
    private val retrofitBreakingBadApi: RetrofitBreakingBadApi,
    private val retrofitBreakingBadCharacterMapper: RetrofitBreakingBadCharacterMapper) :
    NetworkCharactersService {

    override fun getCharacter(id: Int): BreakingBadCharacter {
        val response = retrofitBreakingBadApi.getCharacter(id).execute()
        if (response.isSuccessful) {
            return response.body()!!.map { retrofitBreakingBadCharacterMapper.map(it) }.firstOrNull() ?:
            throw Resources.NotFoundException("There is not character with id = ${id}")
        } else {
            throw Exception(response.errorBody()?.string())
        }
    }

    override fun getCharacters(): List<BreakingBadCharacter> {
        val response = retrofitBreakingBadApi.getCharacters().execute()
        if (response.isSuccessful) {
            return response.body()!!.map { retrofitBreakingBadCharacterMapper.map(it) }
        } else {
            throw Exception(response.errorBody()?.string())
        }
    }

}