package com.rods.data.character.api.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Characters")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false) val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)