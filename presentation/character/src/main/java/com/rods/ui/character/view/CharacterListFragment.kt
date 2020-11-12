package com.rods.ui.character.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rods.domain.character.model.MarvelCharacter
import com.rods.ui.character.R
import com.rods.ui.character.view.adapter.CharacterAdapter
import com.rods.ui.character.view.navigation.CharacterListNavigation
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.characters_list_fragment.*

class CharacterListFragment: Fragment(R.layout.characters_list_fragment) {
    private val viewModel: CharacterListViewModel by viewModel()
    private val navigation: CharacterListNavigation by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadInitialCharacters()
        observeData()

        character_list.onDetectEndOfScroll { viewModel.loadNextPage() }
    }

    private fun observeData() {
        viewModel.marvelCharacters.observe(viewLifecycleOwner, { charactersPage ->
            if (character_list.adapter == null) {
                val adapter = CharacterAdapter().apply {
                    favoriteClickListener = ::onFavoriteClickListener
                    defaultClickListener = ::onDefaultClickListener
                }

                character_list.adapter = adapter
            }

            (character_list.adapter as CharacterAdapter).insertCharacters(charactersPage)
        })
        viewModel.uiState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Waiting -> displayLoading()
                is UIState.DisplayingUI -> displayCharactersList()
                is UIState.NetworkError -> displayError(getString(R.string.default_network_error))
                is UIState.DefaultError -> displayError(it.error?.message)
                is UIState.PaginationError -> showSnackbar(R.string.pagination_error)
            }
        })
    }

    private fun onFavoriteClickListener(character: MarvelCharacter) {
        viewModel.favoriteCharacter(character)
    }

    private fun onDefaultClickListener(character: MarvelCharacter) {
    }

    private fun hideAll() {
        character_list.visibility = View.GONE
        error_container.visibility = View.GONE
        loading.visibility = View.GONE
    }

    private fun displayCharactersList() {
        hideAll()
        character_list.visibility = View.VISIBLE
    }

    private fun displayLoading() {
        hideAll()
        loading.visibility = View.VISIBLE
    }

    private fun displayError(errorDescription: String?) {
        hideAll()
        error_container.visibility = View.VISIBLE

        error_description.text = errorDescription
        retry_btn.setOnClickListener { viewModel.loadInitialCharacters() }
    }

    private fun showSnackbar(msgId: Int) = Snackbar.make(
        requireView(), msgId, Snackbar.LENGTH_LONG
    ).show()

    private fun RecyclerView.onDetectEndOfScroll(listener: () -> Unit) {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    listener.invoke()
                }
            }
        })
    }
}