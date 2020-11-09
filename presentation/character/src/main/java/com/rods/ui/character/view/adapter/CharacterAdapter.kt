package com.rods.ui.character.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rods.domain.character.model.CharactersPage
import com.rods.domain.character.model.MarvelCharacter
import com.rods.ui.character.R

private enum class ViewType(val id: Int) {
    LOADING(0), DEFAULT(1)
}

class CharacterAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val marvelCharacters = mutableListOf<MarvelCharacter>()
    private var hasMoreLoadableData = false

    fun insertCharacters(page: CharactersPage) {
        marvelCharacters.addAll(page.characters)
        hasMoreLoadableData = page.hasMorePages
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val viewType =
            if ((position == itemCount - 1) && hasMoreLoadableData) ViewType.LOADING
            else ViewType.DEFAULT

        return viewType.id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        ViewType.DEFAULT.id -> MarvelCharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.character_cell, parent, false)
        )
        else -> LoadingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.loading_cell, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MarvelCharacterViewHolder)
            holder.bind(marvelCharacters[position])
    }

    override fun getItemCount(): Int {
        var totalSize = marvelCharacters.size
        if (hasMoreLoadableData) totalSize += 1
        return totalSize
    }

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

    inner class LoadingViewHolder(
        private val rootView: View
    ) : RecyclerView.ViewHolder(rootView)
}
