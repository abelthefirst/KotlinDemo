package com.test.kotlindemo.data.network

import android.content.res.Resources
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.service.NetworkCharactersService
import com.test.kotlindemo.data.api.RetrofitBreakingBadApi
import io.reactivex.Flowable

class NetworkCharactersServiceImpl(
    private val retrofitBreakingBadApi: RetrofitBreakingBadApi,
    private val retrofitBreakingBadCharacterMapper: RetrofitBreakingBadCharacterMapper) :
    NetworkCharactersService {

    override fun getCharacter(id: Int): Flowable<BreakingBadCharacter?> {
        return retrofitBreakingBadApi.getCharacter(id).map { retrofitBreakingBadCharacterMapper.map(it).getOrNull(0).also { if (it == null) throw Resources.NotFoundException("There is not character with id = ${id}") } }
    }

    override fun getCharacters(): Flowable<List<BreakingBadCharacter>> {
        return retrofitBreakingBadApi.getCharacters().map { retrofitBreakingBadCharacterMapper.map(it) }
    }

}