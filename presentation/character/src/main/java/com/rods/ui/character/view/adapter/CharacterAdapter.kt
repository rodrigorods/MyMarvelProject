package com.rods.ui.character.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rods.domain.character.model.MarvelCharacter
import com.rods.ui.character.R

class CharacterAdapter(
    private val marvelCharacters: List<MarvelCharacter>
) : RecyclerView.Adapter<CharacterAdapter.MarvelCharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarvelCharacterViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.character_cell, parent, false)
        return MarvelCharacterViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MarvelCharacterViewHolder, position: Int) {
        holder.bind(marvelCharacters[position])
    }

    override fun getItemCount() = marvelCharacters.size

    inner class MarvelCharacterViewHolder(
        private val rootView: View
    ) : RecyclerView.ViewHolder(rootView) {

        private var name: TextView = rootView.findViewById(R.id.name)
        private var description: TextView = rootView.findViewById(R.id.description)
        private var thumbnail: ImageView = rootView.findViewById(R.id.thumbnail)

        fun bind(character: MarvelCharacter) {
            name.text = character.name
            description.text = character.description
            thumbnail.download(character.thumbnailUrl)
        }

        private fun ImageView.download(imageLink: String) {
            Glide.with(context)
                .load(imageLink)
                .fitCenter()
                .into(this)
        }
    }
}
