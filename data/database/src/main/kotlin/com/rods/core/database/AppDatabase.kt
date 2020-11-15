package com.rods.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rods.data.character.api.database.dao.CharacterDao
import com.rods.data.character.api.database.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun charactersLocalDao(): CharacterDao
}