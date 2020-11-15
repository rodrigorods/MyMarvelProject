package com.rods.domain.character.di

import com.rods.domain.character.usecase.CharactersUseCase
import com.rods.domain.character.usecase.CharactersUseCaseImpl
import org.koin.dsl.module

val characterDomainModule = module {
    factory<CharactersUseCase> { CharactersUseCaseImpl(get()) }
}