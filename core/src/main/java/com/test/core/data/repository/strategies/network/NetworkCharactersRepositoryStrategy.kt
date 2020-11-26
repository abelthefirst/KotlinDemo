package com.test.core.data.repository.strategies.network

import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

class NetworkCharactersRepositoryStrategy(private val networkCharactersService: NetworkCharactersService) :
    CharactersRepositoryStrategy {

    override fun getCharacter(id: Int): BreakingBadCharacter? = networkCharactersService.getCharacter(id)

    override fun getCharacters(): List<BreakingBadCharacter> = networkCharactersService.getCharacters()

}