package com.rods.ui.character.view

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
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
        viewModel.marvelCharacters.observe(viewLifecycleOwner, {
            if (character_list.adapter == null)
                character_list.adapter = CharacterAdapter()

            (character_list.adapter as CharacterAdapter).insertCharacters(it)
        })
        viewModel.uiState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Waiting -> displayCharactersList()
                is UIState.DisplayingUI -> displayLoading()
                is UIState.NetworkError -> displayError(getString(R.string.default_network_error))
                is UIState.DefaultError -> displayError(it.error?.message)
                is UIState.PaginationError -> showSnackbar(R.string.pagination_error)
            }
        })
    }

    private fun hideAll() {
        character_list.visibility = View.GONE
        error_container.visibility = View.GONE
        loading.visibility = View.GONE
    }

    private fun displayCharactersList() {
        hideAll()
        loading.visibility = View.VISIBLE
    }

    private fun displayLoading() {
        hideAll()
        character_list.visibility = View.VISIBLE
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