package com.rods.ui.character.view.navigation

import android.os.Bundle

interface CharacterDetailNavigation {
    fun init(bundle: Bundle)
    fun getName(): String
    fun getDescription(): String
    fun getThumbnailLink(): String
}