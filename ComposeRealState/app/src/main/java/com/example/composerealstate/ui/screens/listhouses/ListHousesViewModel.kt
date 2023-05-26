package com.example.composerealstate.ui.screens.listhouses

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerealstate.common.Constants
import com.example.composerealstate.domain.usecases.house.GetAllHousesCachedUseCase
import com.example.composerealstate.domain.usecases.house.GetAllHousesUseCase
import com.example.composerealstate.network.NetworkResult
import com.example.composerealstate.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("StaticFieldLeak")
@HiltViewModel
class ListHousesViewModel @Inject constructor(
    @ApplicationContext val appContext: Context,
    private val getAllHousesUseCase: GetAllHousesUseCase,
    private val getAllHousesCachedUseCase: GetAllHousesCachedUseCase
) : ViewModel() {
    private val _uiStateFlow: MutableStateFlow<ListHousesContract.ListHousesState> =
        MutableStateFlow(
            ListHousesContract.ListHousesState()
        )
    val uiStateFlow: StateFlow<ListHousesContract.ListHousesState> = _uiStateFlow.asStateFlow()

    fun handleEvent(event: ListHousesContract.ListHousesEvents) {
        when (event) {
            ListHousesContract.ListHousesEvents.LoadHouses -> {
                getHouses()
            }
            is ListHousesContract.ListHousesEvents.OnSearchQueryChange -> {
                onQueryChange(event.searchQuery)
            }
            ListHousesContract.ListHousesEvents.OnErrorShown -> {
                _uiStateFlow.update { it.copy(error = null) }
            }
        }
    }
    private fun onQueryChange(query: String) {
        _uiStateFlow.update {
            it.copy(searchQuery = query)
        }
        filterHouses(query)
    }

    /**
     * Gets the houses checking if the device has internet connection or not.
     *
     * If the device has internet connection, we get the data [getHousesWithInternet]
     *
     * If the device has no internet connection, we get the data [getHousesWithNoInternet]
     *
     */
    private fun getHouses() {
        if (Utils.hasInternetConnection(context = appContext)) {
            getHousesWithInternet()
        } else {
            getHousesWithNoInternet()
        }
    }

    /**
     * Gets the houses, if there is any error, it will be shown in the UI.
     */
    private fun getHousesWithInternet() {
        viewModelScope.launch {
            getAllHousesUseCase.invoke().catch { e ->
                _uiStateFlow.update { it.copy(error = e.message) }
            }.collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiStateFlow.update {
                            it.copy(
                                houses = result.data!!,
                                isLoading = false,
                                noResultsFound = false,
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiStateFlow.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Error -> {
                        _uiStateFlow.update { it.copy(error = result.message, isLoading = false) }
                    }
                }
            }
        }
    }

    /**
     * Gets the houses, with an error message indicating that there is no internet connection.
     */
    private fun getHousesWithNoInternet() {
        viewModelScope.launch {
            getAllHousesUseCase.invoke().catch { e ->
                _uiStateFlow.update { it.copy(error = e.message) }
            }.collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiStateFlow.update {
                            it.copy(
                                houses = result.data!!,
                                isLoading = false,
                                noResultsFound = false,
                                error = Constants.NO_INTERNET_CONNECTION_ERROR
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                        _uiStateFlow.update { it.copy(isLoading = true) }
                    }
                    is NetworkResult.Error -> {
                        _uiStateFlow.update { it.copy(error = result.message, isLoading = false) }
                    }
                }
            }
        }
    }

    private fun filterHouses(
        searchQuery: String
    ) {
        if (searchQuery.isEmpty()) {
            getHouses()
        } else {
            val filteredHouses = _uiStateFlow.value.houses.filter {
                it.zipCode.contains(searchQuery, true) || it.city.contains(searchQuery, true)
            }
            if (filteredHouses.isNotEmpty()) {
                _uiStateFlow.update {
                    it.copy(
                        houses = filteredHouses,
                        noResultsFound = false,
                        error = null
                    )
                }
            } else {
                _uiStateFlow.update {
                    it.copy(
                        noResultsFound = true
                    )
                }
            }
        }
    }
}