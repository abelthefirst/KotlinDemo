package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter

interface LocalStorageCharactersService {

    fun clearCharacters()

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): List<BreakingBadCharacter>

    fun setCharacter(breakingBadCharacter: BreakingBadCharacter)

    fun setCharacters(breakingBadCharacters: List<BreakingBadCharacter>) {
        breakingBadCharacters.forEach { setCharacter(it) }
    }

}
