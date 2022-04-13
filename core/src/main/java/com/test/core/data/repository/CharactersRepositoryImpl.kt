package com.test.core.data.repository

import com.test.core.data.repository.strategies.CharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService
import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.strategies.localstorage.LocalStorageCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.memory.MemoryCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.network.NetworkCharactersRepositoryStrategy

class CharactersRepositoryImpl(
    localStorageService: LocalStorageCharactersService,
    networkCharactersService: NetworkCharactersService
) : CharactersRepository {

    private val upstreamStrategy: CharactersRepositoryStrategy

    private var hasMore: Boolean = true
    private var offset: Int = 0

    init {
        val networkStrategy = NetworkCharactersRepositoryStrategy(networkCharactersService)
        val localStorageStrategy = LocalStorageCharactersRepositoryStrategy(localStorageService, networkStrategy)
        val memoryStrategy = MemoryCharactersRepositoryStrategy(localStorageStrategy)

        upstreamStrategy = memoryStrategy
    }

    override fun getCharacter(id: Int): BreakingBadCharacter? = upstreamStrategy.getCharacter(id)

    override fun getCharacters(): BreakingBadCharacterListResult = upstreamStrategy.getCharacters().also {
        it.updateRepositoryState()
    }

    override fun getMoreCharacters(): BreakingBadCharacterListResult {
        if (!hasMore) {
            throw IllegalStateException("There are no more items in the repository")
        }

        return upstreamStrategy.getCharacters(offset).also {
            it.updateRepositoryState()
        }
    }

    override fun refreshCharacters(): BreakingBadCharacterListResult {
        upstreamStrategy.clearCharacters()

        hasMore = true
        offset = 0

        return getMoreCharacters()
    }

    private fun BreakingBadCharacterListResult.updateRepositoryState() {
        this@CharactersRepositoryImpl.hasMore = hasMore
        this@CharactersRepositoryImpl.offset += characters.size
    }

}
