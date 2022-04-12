package com.test.core.data.repository.strategies.network

import com.test.core.data.repository.BreakingBadCharacterListResult
import com.test.core.data.repository.CharactersRepositoryStrategy
import com.test.core.service.NetworkCharactersService

internal class NetworkCharactersRepositoryStrategy(private val networkCharactersService: NetworkCharactersService) :
    CharactersRepositoryStrategy {

    override fun clearCharacters() {}

    override fun getCharacter(id: Int) = networkCharactersService.getCharacter(id)

    override fun getCharacters() = getCharacters(0)

    override fun getCharacters(offset: Int): BreakingBadCharacterListResult {
        val characters = networkCharactersService.getCharacters(
            offset = offset,
            pageSize = PAGE_SIZE
        ).sortedBy { it.id }

        return BreakingBadCharacterListResult(
            characters = characters,
            hasMore = characters.size == PAGE_SIZE
        )
    }

    companion object {

        private const val PAGE_SIZE = 10

    }

}
