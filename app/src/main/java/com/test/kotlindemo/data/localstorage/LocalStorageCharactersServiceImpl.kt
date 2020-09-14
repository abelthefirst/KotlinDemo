package com.test.kotlindemo.data.localstorage

import com.test.core.service.LocalStorageCharactersService
import com.test.core.data.repository.BreakingBadCharacter
import com.test.kotlindemo.data.room.BreakingBadCharactersRoomDatabase
import io.reactivex.Flowable

class LocalStorageCharactersServiceImpl(
    private val breakingBadCharactersRoomDatabase: BreakingBadCharactersRoomDatabase,
    private val breakingBadCharacterEntityMapper: BreakingBadCharacterEntityMapper):
    LocalStorageCharactersService {

    override fun getCharacter(id: Int): Flowable<List<BreakingBadCharacter>> {
        return breakingBadCharactersRoomDatabase.breakingBadCharacterDao().getCharacter(id).map { breakingBadCharacterEntityMapper.map(it) }
    }

    override fun getCharacters(): Flowable<List<BreakingBadCharacter>> {
        return breakingBadCharactersRoomDatabase.breakingBadCharacterDao().getCharacters().map { breakingBadCharacterEntityMapper.map(it) }
    }

    override fun setCharacter(breakingBadCharacter: BreakingBadCharacter) {
        breakingBadCharactersRoomDatabase.breakingBadCharacterDao().setCharacter(breakingBadCharacterEntityMapper.map(breakingBadCharacter))
    }

}