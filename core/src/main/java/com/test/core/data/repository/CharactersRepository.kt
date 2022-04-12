package com.test.core.data.repository

interface CharactersRepository {

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): BreakingBadCharacterListResult

    fun getMoreCharacters(): BreakingBadCharacterListResult

    fun refreshCharacters(): BreakingBadCharacterListResult

}
