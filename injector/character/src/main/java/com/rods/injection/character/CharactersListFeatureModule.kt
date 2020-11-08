package com.rods.injection.character

import com.rods.data.character.api.di.characterApiModule
import com.rods.domain.character.di.characterDomainModule
import com.rods.ui.character.di.uiModule
import org.koin.dsl.module

val charactersListFeatureModule = listOf(
    uiModule, characterDomainModule, characterApiModule
)