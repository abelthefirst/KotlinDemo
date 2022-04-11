package com.test.kotlindemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BreakingBadCharacterDao {

    @Query("SELECT COUNT(1) FROM characters WHERE id = :id")
    fun contains(id: Int): Boolean

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): List<BreakingBadCharacterEntity>

    @Query("SELECT * FROM characters")
    fun getCharacters(): List<BreakingBadCharacterEntity>

    @Query("SELECT COUNT(1) FROM characters")
    fun isNotEmpty(): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCharacter(breakingBadCharacterDao: BreakingBadCharacterEntity?)

}
