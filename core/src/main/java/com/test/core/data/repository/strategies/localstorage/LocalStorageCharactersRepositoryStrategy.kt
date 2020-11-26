package com.test.core.data.repository.strategies.localstorage

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService

class LocalStorageCharactersRepositoryStrategy(
    private val localStorageCharactersService: LocalStorageCharactersService,
    private val upstream: CharactersRepositoryStrategy
) : CharactersRepositoryStrategy {

    override fun getCharacter(id: Int): BreakingBadCharacter? {
        return localStorageCharactersService.getCharacter(id) ?:
            upstream.getCharacter(id)?.also { localStorageCharactersService.setCharacter(it) }
    }

    override fun getCharacters(): List<BreakingBadCharacter> {
        var characterList = localStorageCharactersService.getCharacters()
        if (characterList.isEmpty()) {
            characterList = upstream.getCharacters().also { localStorageCharactersService.setCharacters(it) }
        }

        return characterList
    }

}