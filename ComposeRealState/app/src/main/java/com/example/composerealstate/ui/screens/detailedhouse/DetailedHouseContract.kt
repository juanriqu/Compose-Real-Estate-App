package com.example.composerealstate.ui.screens.detailedhouse

import com.example.composerealstate.domain.model.House

interface DetailedHouseContract {
    /**
     * UI State that represents DetailedHouseScreen
     **/
    data class DetailedHouseState(
        val house : House,
        val error: String? = null
    )

    /**
     * DetailedHouse Events that can be triggered by the user
     * */
    sealed class DetailedHouseEvent {
        class GetHouse(val houseId: Int) : DetailedHouseEvent()
        object OnErrorShown : DetailedHouseEvent()
    }
}

