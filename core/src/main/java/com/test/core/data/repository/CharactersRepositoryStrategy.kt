package com.test.core.data.repository

interface CharactersRepositoryStrategy {

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): BreakingBadCharacterListResult

}
