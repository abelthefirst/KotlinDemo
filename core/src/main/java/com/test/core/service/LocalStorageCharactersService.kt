package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter
import io.reactivex.Flowable

interface LocalStorageCharactersService {

    fun getCharacter(id: Int): Flowable<List<BreakingBadCharacter>>

    fun getCharacters(): Flowable<List<BreakingBadCharacter>>

    fun setCharacter(breakingBadCharacter: BreakingBadCharacter)

    fun setCharacters(breakingBadCharacters: List<BreakingBadCharacter>) {
        breakingBadCharacters.forEach { setCharacter(it) }
    }

}