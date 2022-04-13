package com.test.core.data.repository.strategies.localstorage

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.strategies.CharactersRepositoryStrategy
import com.test.core.data.repository.strategies.NonPaginatedCharactersRepositoryStrategy
import com.test.core.data.repository.strategies.PersistentCharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService

internal class LocalStorageCharactersRepositoryStrategy(
    private val localStorageCharactersService: LocalStorageCharactersService,
    upstream: CharactersRepositoryStrategy
) : NonPaginatedCharactersRepositoryStrategy(upstream), PersistentCharactersRepositoryStrategy {

    override fun clearCharactersImpl() = localStorageCharactersService.clearCharacters()

    override fun loadCharacter(id: Int) = localStorageCharactersService.getCharacter(id)

    override fun loadCharacters() = localStorageCharactersService.getCharacters()

    override fun setCharacter(character: BreakingBadCharacter) {
        localStorageCharactersService.setCharacter(character)
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) {
        localStorageCharactersService.setCharacters(characters)
    }

}
