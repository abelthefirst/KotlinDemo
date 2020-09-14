package com.test.core.data.repository

import io.reactivex.Flowable

interface CharactersRepositoryStrategy {

    fun getCharacter(id: Int): Flowable<BreakingBadCharacter?>

    fun getCharacters(): Flowable<List<BreakingBadCharacter>>

}