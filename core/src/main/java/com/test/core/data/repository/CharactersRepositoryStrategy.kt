package com.test.core.data.repository

internal interface CharactersRepositoryStrategy {

    fun clearCharacters()

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): BreakingBadCharacterListResult

    fun getCharacters(offset: Int): BreakingBadCharacterListResult

}
