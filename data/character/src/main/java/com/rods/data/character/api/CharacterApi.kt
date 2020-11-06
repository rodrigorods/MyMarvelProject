package com.rods.data.character.api

import com.rods.data.character.api.model.CharactersResponse
import com.rods.data.utils.model.RawResponse
import retrofit2.http.GET

interface CharacterApi {

    @GET("/v1/public/characters")
    suspend fun getCharacters(): RawResponse<CharactersResponse>

}
