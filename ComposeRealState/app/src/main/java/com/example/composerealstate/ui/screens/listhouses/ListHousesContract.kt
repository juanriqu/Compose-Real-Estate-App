package com.example.composerealstate.ui.screens.listhouses

import com.example.composerealstate.domain.model.House

interface ListHousesContract {

    /**
     * UI State that represents ListHousesScreen
     **/
    data class ListHousesState(
        val houses: List<House> = emptyList(),
        val searchQuery: String = "",
        val noResultsFound: Boolean = false,
        val isLoading: Boolean = false,
        val error: String? = null
    )

    sealed class ListHousesEvents {
        object LoadHouses : ListHousesEvents()
        class OnSearchQueryChange(val searchQuery: String) : ListHousesEvents()
        object OnErrorShown : ListHousesEvents()
    }
}

