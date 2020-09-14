package com.test.kotlindemo.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DB_NAME = "banking_database.db"
private const val DB_VERSION = 1

@Database(entities = [BreakingBadCharacterEntity::class], version = DB_VERSION, exportSchema = false)
abstract class BreakingBadCharactersRoomDatabase : RoomDatabase() {

    companion object {

        private var instance: BreakingBadCharactersRoomDatabase? = null

        fun get(context: Context): BreakingBadCharactersRoomDatabase {
            return instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): BreakingBadCharactersRoomDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                BreakingBadCharactersRoomDatabase::class.java,
                DB_NAME)
                .build()
        }

    }

    abstract fun breakingBadCharacterDao(): BreakingBadCharacterDao

}