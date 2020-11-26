package com.test.kotlindemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreakingBadCharacterDao {

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): List<BreakingBadCharacterEntity>

    @Query("SELECT * FROM characters")
    fun getCharacters(): List<BreakingBadCharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCharacter(breakingBadCharacterDao: BreakingBadCharacterEntity?)

}