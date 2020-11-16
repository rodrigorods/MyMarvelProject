package com.rods.mymarvelproject.di

import com.rods.mymarvelproject.navigation.CharacterDetailNavigationImpl
import com.rods.mymarvelproject.navigation.CharacterListNavigationImpl
import com.rods.ui.character.view.navigation.CharacterDetailNavigation
import com.rods.ui.character.view.navigation.CharacterListNavigation
import org.koin.dsl.module

val navigationModule = module {
    factory<CharacterListNavigation> { CharacterListNavigationImpl() }
    factory<CharacterDetailNavigation> { CharacterDetailNavigationImpl() }
}