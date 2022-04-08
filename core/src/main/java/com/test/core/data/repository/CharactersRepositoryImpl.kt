package com.test.core.data.repository

import com.test.core.service.LocalStorageCharactersService
import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.strategies.localstorage.LocalStorageCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.memory.MemoryCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.network.NetworkCharactersRepositoryStrategy

class CharactersRepositoryImpl(
    localStorageService: LocalStorageCharactersService,
    networkCharactersService: NetworkCharactersService
) : CharactersRepository {

    private val upstream: CharactersRepositoryStrategy

    init {
        val networkStrategy = NetworkCharactersRepositoryStrategy(networkCharactersService)
        val localStorageStrategy = LocalStorageCharactersRepositoryStrategy(localStorageService, networkStrategy)
        val memoryStrategy = MemoryCharactersRepositoryStrategy(localStorageStrategy)

        upstream = memoryStrategy
    }

    override suspend fun getCharacter(id: Int): BreakingBadCharacter? = upstream.getCharacter(id)

    override suspend fun getCharacters(): List<BreakingBadCharacter> = upstream.getCharacters()

}