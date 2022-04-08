package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter

interface NetworkCharactersService {

    suspend fun getCharacter(id: Int): BreakingBadCharacter?

    suspend fun getCharacters(): List<BreakingBadCharacter>

}