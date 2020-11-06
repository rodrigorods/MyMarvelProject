package com.rods.data.character.api.di

import com.rods.data.character.api.CharacterApi
import com.rods.data.character.api.datasource.CharacterDataSource
import com.rods.data.character.api.datasource.remote.CharactersRemoteDataSource
import com.rods.data.character.api.repository.CharactersRepositoryImpl
import com.rods.data.utils.builder.createApi
import com.rods.domain.character.repository.CharactersRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val characterApiModule = module {
    factory { createApi<CharacterApi>(get()) }
    factory<CharactersRepository> { CharactersRepositoryImpl(get(named("remote"))) }
    factory<CharacterDataSource>(named("remote")) { CharactersRemoteDataSource(get()) }
}
