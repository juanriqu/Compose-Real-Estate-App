package com.example.composerealstate.ui.screens.detailedhouse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.domain.usecases.house.GetHouseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

@HiltViewModel
class DetailedHouseViewModel @Inject constructor(
    private val getHouseUseCase: GetHouseUseCase,
) : ViewModel() {
    /**
     * We put a default value because it needs to be initialized
     * */
    private val _uiStateFlow: MutableStateFlow<DetailedHouseContract.DetailedHouseState> =
        MutableStateFlow(DetailedHouseContract.DetailedHouseState(
            House(
                0, "", 45, 4, 3, 4, "", "", "", 4, 4, ""
            ),   null
        ))

    val uiStateFlow: StateFlow<DetailedHouseContract.DetailedHouseState> = _uiStateFlow.asStateFlow()

    fun handleEvent(event: DetailedHouseContract.DetailedHouseEvent) {
        when (event) {
            is DetailedHouseContract.DetailedHouseEvent.GetHouse -> {
                getHouse(event.houseId)
            }
            DetailedHouseContract.DetailedHouseEvent.OnErrorShown -> {
                _uiStateFlow.value = _uiStateFlow.value.copy(error = null)
            }
        }
    }

    private fun getHouse(houseId: Int) {
        viewModelScope.launch {
            getHouseUseCase.invoke(houseId).catch {
                _uiStateFlow.value = _uiStateFlow.value.copy(error = it.message)
            }.collect { result ->
                _uiStateFlow.value = _uiStateFlow.value.copy(house = result)
            }
        }
    }
}