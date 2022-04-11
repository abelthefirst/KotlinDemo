package com.test.core.data.repository.strategies.localstorage

import com.test.core.data.repository.BaseCharactersRepositoryStrategy
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService

internal class LocalStorageCharactersRepositoryStrategy(
    private val localStorageCharactersService: LocalStorageCharactersService,
    override val upstream: CharactersRepositoryStrategy
) : BaseCharactersRepositoryStrategy() {

    override fun loadCharacter(id: Int) = localStorageCharactersService.getCharacter(id)

    override fun loadCharacters() = localStorageCharactersService.getCharacters()

    override fun setCharacter(character: BreakingBadCharacter) {
        localStorageCharactersService.setCharacter(character)
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) {
        localStorageCharactersService.setCharacters(characters)
    }

}
