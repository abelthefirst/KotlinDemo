package com.test.core.data.repository.strategies.network

import com.test.core.data.repository.BaseCharactersRepositoryStrategy
import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy

internal class NetworkCharactersRepositoryStrategy(private val networkCharactersService: NetworkCharactersService) :
    BaseCharactersRepositoryStrategy() {

    override val upstream: CharactersRepositoryStrategy? = null

    override fun loadCharacter(id: Int) = networkCharactersService.getCharacter(id)

    override fun loadCharacters() = networkCharactersService.getCharacters()

    override fun setCharacter(character: BreakingBadCharacter) {
        throw IllegalStateException("This method should not be called.")
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) {
        throw IllegalStateException("This method should not be called.")
    }

}