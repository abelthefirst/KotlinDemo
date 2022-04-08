package com.test.kotlindemo.data.network

import android.content.res.Resources
import com.test.core.service.NetworkCharactersService
import com.test.kotlindemo.data.api.RetrofitBreakingBadApi

class NetworkCharactersServiceImpl(
    private val retrofitBreakingBadApi: RetrofitBreakingBadApi,
    private val retrofitBreakingBadCharacterMapper: RetrofitBreakingBadCharacterMapper) :
    NetworkCharactersService {

    override suspend fun getCharacter(id: Int) = retrofitBreakingBadApi
        .getCharacter(id)
        .map { retrofitBreakingBadCharacterMapper.map(it) }
        .firstOrNull() ?: throw Resources.NotFoundException("There is not character with id = ${id}")

    override suspend fun getCharacters() = retrofitBreakingBadApi
        .getCharacters()
        .map { retrofitBreakingBadCharacterMapper.map(it) }

}