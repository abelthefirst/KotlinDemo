package com.test.kotlindemo.data.localstorage

import com.test.core.data.repository.BreakingBadCharacter
import com.test.core.service.LocalStorageCharactersService
import com.test.kotlindemo.data.room.BreakingBadCharactersRoomDatabase

class LocalStorageCharactersServiceImpl(
    private val breakingBadCharactersRoomDatabase: BreakingBadCharactersRoomDatabase,
    private val breakingBadCharacterEntityMapper: BreakingBadCharacterEntityMapper):
    LocalStorageCharactersService {

    override suspend fun getCharacter(id: Int): BreakingBadCharacter? {
        return breakingBadCharactersRoomDatabase
            .breakingBadCharacterDao()
            .getCharacter(id)
            .map { entity -> breakingBadCharacterEntityMapper.map(entity) }
            .firstOrNull()
    }

    override suspend fun getCharacters(): List<BreakingBadCharacter> {
        return breakingBadCharactersRoomDatabase.breakingBadCharacterDao().getCharacters().map { breakingBadCharacterEntityMapper.map(it) }
    }

    override suspend fun setCharacter(breakingBadCharacter: BreakingBadCharacter) {
        breakingBadCharactersRoomDatabase.breakingBadCharacterDao().setCharacter(breakingBadCharacterEntityMapper.map(breakingBadCharacter))
    }

}