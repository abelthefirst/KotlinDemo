package com.test.core.data.repository

interface CharactersRepository {

    suspend fun getCharacter(id: Int): BreakingBadCharacter?

    suspend fun getCharacters(): List<BreakingBadCharacter>

}