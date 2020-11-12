package com.rods.data.character.api.datasource

class CharacterDataSourceProvider(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterLocalDataSource
) {
    fun getRemote() = remoteDataSource
    fun getLocal() = localDataSource
}