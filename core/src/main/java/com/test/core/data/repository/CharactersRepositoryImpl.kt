package com.test.core.data.repository

import com.test.core.service.LocalStorageCharactersService
import com.test.core.service.NetworkCharactersService
import com.test.core.data.repository.strategies.localstorage.LocalStorageCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.memory.MemoryCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.network.NetworkCharactersRepositoryStrategy
import io.reactivex.Flowable

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

    override fun getCharacter(id: Int): Flowable<BreakingBadCharacter?> = upstream.getCharacter(id)

    override fun getCharacters(): Flowable<List<BreakingBadCharacter>> = upstream.getCharacters()

}