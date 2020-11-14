package com.rods.data.character.api

import com.rods.data.character.api.model.CharactersResponse
import com.rods.data.utils.model.RawResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("limit") batchSize: Int,
        @Query("offset") offset: Int,
        @Query("name") characterName: String?
    ): RawResponse<CharactersResponse>

}
