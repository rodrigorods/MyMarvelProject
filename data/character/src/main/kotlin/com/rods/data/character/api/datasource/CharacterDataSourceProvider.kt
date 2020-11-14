package com.rods.data.character.api.datasource

import com.rods.data.character.api.datasource.remote.CharacterRemoteDataSource
import com.rods.data.character.api.datasource.local.CharacterLocalDataSource

class CharacterDataSourceProvider(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) {
    fun getRemote() = remoteDataSource
    fun getLocal() = localDataSource
}