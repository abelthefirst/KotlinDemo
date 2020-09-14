package com.test.kotlindemo.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class BreakingBadCharacterEntity(
    @ColumnInfo(name = "birthday") val birthday: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nickname") val nickname: String,
    @ColumnInfo(name = "status") val status: String
)