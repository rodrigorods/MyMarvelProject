package com.rods.data.character.api.database.dao

import androidx.room.*
import com.rods.data.character.api.database.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM Characters")
    suspend fun findAll(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(character: CharacterEntity)

    @Query("DELETE FROM Characters WHERE id=:characterId")
    suspend fun unfavorite(characterId: Long)
}
