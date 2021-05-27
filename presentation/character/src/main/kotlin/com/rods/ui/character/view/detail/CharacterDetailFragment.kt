package com.rods.ui.character.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.rods.ui.character.R
import com.rods.ui.character.view.navigation.CharacterDetailNavigation
import kotlinx.android.synthetic.main.character_detail_fragment.*
import org.koin.android.ext.android.inject

class CharacterDetailFragment : Fragment(R.layout.character_detail_fragment) {

    private val navigation: CharacterDetailNavigation by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigation.init(requireArguments())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name.text = navigation.getName()
        description.text = navigation.getDescription()

        loadThumbnailIntoImageView()
    }

    private fun loadThumbnailIntoImageView() {
        Glide.with(requireContext())
            .load(navigation.getThumbnailLink())
            .into(character_image)
    }

}