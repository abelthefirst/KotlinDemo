package com.test.kotlindemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreakingBadCharacterDao {

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getCharacter(id: Int): List<BreakingBadCharacterEntity>

    @Query("SELECT * FROM characters")
    suspend fun getCharacters(): List<BreakingBadCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCharacter(breakingBadCharacterDao: BreakingBadCharacterEntity?)

}