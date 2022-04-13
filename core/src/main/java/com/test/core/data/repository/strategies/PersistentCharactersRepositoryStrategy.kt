package com.test.core.data.repository.strategies

import com.test.core.data.repository.BreakingBadCharacter

internal interface PersistentCharactersRepositoryStrategy {

    fun clearCharactersImpl()

    fun setCharacter(character: BreakingBadCharacter)

    fun setCharacters(characters: List<BreakingBadCharacter>)

}
