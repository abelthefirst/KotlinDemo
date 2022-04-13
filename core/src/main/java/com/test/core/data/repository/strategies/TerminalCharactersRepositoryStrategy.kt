package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.BreakingBadCharacterListResult

internal object TerminalCharactersRepositoryStrategy : CharactersRepositoryStrategy {

    override fun clearCharacters() {}

    override fun getCharacter(id: Int): BreakingBadCharacter? = null

    override fun getCharacters() = BreakingBadCharacterListResult(
        characters = emptyList(),
        hasMore = false
    )

    override fun getCharacters(offset: Int) = getCharacters()

}
