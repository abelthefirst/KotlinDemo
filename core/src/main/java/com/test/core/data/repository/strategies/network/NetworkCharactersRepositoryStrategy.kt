package com.test.core.data.repository.strategies.network

import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

class NetworkCharactersRepositoryStrategy(private val networkCharactersService: NetworkCharactersService) :
    CharactersRepositoryStrategy {

    override suspend fun getCharacter(id: Int): BreakingBadCharacter? = networkCharactersService.getCharacter(id)

    override suspend fun getCharacters(): List<BreakingBadCharacter> = networkCharactersService.getCharacters()

}