package com.rods.data.character.api.di

import com.rods.data.character.api.CharacterApi
import com.rods.data.character.api.datasource.CharacterDataSourceProvider
import com.rods.data.character.api.datasource.local.CharacterLocalDataSource
import com.rods.data.character.api.datasource.remote.CharacterRemoteDataSource
import com.rods.data.character.api.datasource.local.CharacterLocalDataSourceImpl
import com.rods.data.character.api.datasource.remote.CharacterRemoteDataSourceImpl
import com.rods.data.character.api.repository.CharactersRepositoryImpl
import com.rods.data.utils.builder.createApi
import com.rods.domain.character.repository.CharactersRepository
import org.koin.dsl.module

val characterApiModule = module {
    factory { createApi<CharacterApi>(get()) }
    factory { CharacterDataSourceProvider(get(), get()) }
    factory<CharactersRepository> { CharactersRepositoryImpl(get()) }
    factory<CharacterRemoteDataSource> { CharacterRemoteDataSourceImpl(get()) }
    factory<CharacterLocalDataSource> { CharacterLocalDataSourceImpl(get()) }
}
