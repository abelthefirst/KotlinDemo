package com.test.core.service

import com.test.core.data.repository.BreakingBadCharacter
import io.reactivex.Flowable

interface NetworkCharactersService {

    fun getCharacter(id: Int): Flowable<BreakingBadCharacter?>

    fun getCharacters(): Flowable<List<BreakingBadCharacter>>

}