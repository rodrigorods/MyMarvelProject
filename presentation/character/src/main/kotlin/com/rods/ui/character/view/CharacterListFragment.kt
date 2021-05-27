package com.rods.ui.character.view

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rods.domain.character.model.MarvelCharacter
import com.rods.ui.character.R
import com.rods.ui.character.view.adapter.CharacterAdapter
import com.rods.ui.character.view.navigation.CharacterListNavigation
import kotlinx.android.synthetic.main.characters_list_fragment.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment: Fragment(R.layout.characters_list_fragment) {
    private val viewModel: CharacterListViewModel by viewModel()
    private val navigation: CharacterListNavigation by inject()

    private val searchManager by lazy { requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadInitialCharacters()
        observeData()
    }

    private fun observeData() {
        viewModel.marvelCharacters.observe(viewLifecycleOwner, { charactersPage ->
            if (character_list.adapter == null) {
                character_list.adapter = generateAdapter()
            }

            if (charactersPage != null) {
                (character_list.adapter as CharacterAdapter).insertCharacters(charactersPage)
                (character_list.adapter as CharacterAdapter).notifyDataSetChanged()
            }
        })
        viewModel.uiState.observe(viewLifecycleOwner, {
            when (it) {
                is UIState.Waiting -> displayLoading()
                is UIState.DisplayingUI,
                is UIState.DisplayingFavorites -> displayCharactersList()
                is UIState.EmptyList -> displayError(getString(R.string.error_empty_charaters_list))
                is UIState.NetworkError -> displayError(getString(R.string.default_network_error))
                is UIState.DefaultError -> displayError(it.error?.message)
                is UIState.PaginationError -> showSnackbar(R.string.pagination_error)
            }
        })
    }

    private fun generateAdapter() = CharacterAdapter().apply {
        favoriteClickListener = ::onFavoriteClickListener
        defaultClickListener = ::onDefaultClickListener
        onLoadingDisplayedListener = { viewModel.loadNextPage() }
    }

    private fun onFavoriteClickListener(character: MarvelCharacter) {
        viewModel.favoriteCharacter(character)
    }

    private fun onDefaultClickListener(character: MarvelCharacter) {
        with(character) {
            navigation.openCharacterDetail(this@CharacterListFragment, name, description, thumbnailUrl)
        }
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
        retry_btn.setOnClickListener {
            viewModel.resetLoadingData()
            viewModel.loadInitialCharacters()
        }
    }

    private fun showSnackbar(msgId: Int) = Snackbar.make(
        requireView(), msgId, Snackbar.LENGTH_LONG
    ).show()

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.characters_search_menu, menu)
        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.searchTerm = query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) viewModel.searchTerm = null
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_list_favorites) {
            viewModel.toogleFavoriteList()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}