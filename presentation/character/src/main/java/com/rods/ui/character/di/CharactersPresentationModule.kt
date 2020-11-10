package com.rods.ui.character.di

import com.rods.ui.character.view.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { CharacterListViewModel(get(), get()) }
}