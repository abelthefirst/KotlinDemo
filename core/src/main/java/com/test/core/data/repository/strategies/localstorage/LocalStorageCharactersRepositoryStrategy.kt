package com.test.core.data.repository.strategies.localstorage

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService
import io.reactivex.Flowable

class LocalStorageCharactersRepositoryStrategy(
    private val localStorageCharactersService: LocalStorageCharactersService,
    private val upstream: CharactersRepositoryStrategy
) : CharactersRepositoryStrategy {

    override fun getCharacter(id: Int): Flowable<BreakingBadCharacter?> {
        return localStorageCharactersService.getCharacter(id).flatMap { if (!it.isEmpty()) Flowable.fromCallable { it[0] } else upstream.getCharacter(id).doOnNext { localStorageCharactersService.setCharacter(it!!) } }
    }

    override fun getCharacters(): Flowable<List<BreakingBadCharacter>> {
        return localStorageCharactersService.getCharacters().flatMap { if (!it.isEmpty()) Flowable.fromCallable { it } else upstream.getCharacters().doOnNext { localStorageCharactersService.setCharacters(it) } }
    }

}