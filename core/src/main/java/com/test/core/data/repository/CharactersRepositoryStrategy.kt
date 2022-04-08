package com.test.core.data.repository

interface CharactersRepositoryStrategy {

    suspend fun getCharacter(id: Int): BreakingBadCharacter?

    suspend fun getCharacters(): List<BreakingBadCharacter>

}