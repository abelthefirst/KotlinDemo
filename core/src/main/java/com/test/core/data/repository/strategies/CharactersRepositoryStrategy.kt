package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.data.repository.BreakingBadCharacterListResult

internal interface CharactersRepositoryStrategy {

    fun clearCharacters()

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): BreakingBadCharacterListResult

    fun getCharacters(offset: Int): BreakingBadCharacterListResult

}
