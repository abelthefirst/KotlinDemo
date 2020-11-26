package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter

interface NetworkCharactersService {

    fun getCharacter(id: Int): BreakingBadCharacter?

    fun getCharacters(): List<BreakingBadCharacter>

}