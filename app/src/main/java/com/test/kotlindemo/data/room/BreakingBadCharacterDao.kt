package com.test.kotlindemo.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable

@Dao
interface BreakingBadCharacterDao {

    @Query("SELECT * FROM characters WHERE id = :id")
    fun getCharacter(id: Int): Flowable<List<BreakingBadCharacterEntity>>

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flowable<List<BreakingBadCharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setCharacter(breakingBadCharacterDao: BreakingBadCharacterEntity?)

}