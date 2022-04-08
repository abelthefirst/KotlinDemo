package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter

interface LocalStorageCharactersService {

    suspend fun getCharacter(id: Int): BreakingBadCharacter?

    suspend fun getCharacters(): List<BreakingBadCharacter>

    suspend fun setCharacter(breakingBadCharacter: BreakingBadCharacter)

    suspend fun setCharacters(breakingBadCharacters: List<BreakingBadCharacter>) {
        breakingBadCharacters.forEach { setCharacter(it) }
    }

}