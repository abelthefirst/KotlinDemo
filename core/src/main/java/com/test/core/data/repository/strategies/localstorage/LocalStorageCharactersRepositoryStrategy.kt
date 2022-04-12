package com.test.core.data.repository.strategies.localstorage

import com.test.core.data.repository.CharactersRepositoryStrategyWithAnUpstream
import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.CharactersRepositoryStrategy
import com.test.core.service.LocalStorageCharactersService

internal class LocalStorageCharactersRepositoryStrategy(
    private val localStorageCharactersService: LocalStorageCharactersService,
    override val upstream: CharactersRepositoryStrategy
) : CharactersRepositoryStrategyWithAnUpstream() {

    override fun clearCharactersImpl() = localStorageCharactersService.clearCharacters()

    override fun loadCharacter(id: Int) = localStorageCharactersService.getCharacter(id)

    override fun loadCharacters() = localStorageCharactersService.getCharacters().sortedBy { it.id }

    override fun setCharacter(character: BreakingBadCharacter) {
        localStorageCharactersService.setCharacter(character)
    }

    override fun setCharacters(characters: List<BreakingBadCharacter>) {
        localStorageCharactersService.setCharacters(characters)
    }

}
