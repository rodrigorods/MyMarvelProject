package com.rods.mymarvelproject.di

import com.rods.core.builder.provideOkHttpClient
import com.rods.core.builder.provideRetrofit
import com.rods.core.interceptor.AuthInterceptor
import com.rods.mymarvelproject.BuildConfig
import com.rods.mymarvelproject.navigation.CharacterListNavigationImpl
import com.rods.ui.character.view.navigation.CharacterListNavigation
import org.koin.dsl.module

val navigationModule = module {
    factory<CharacterListNavigation> { CharacterListNavigationImpl() }
}