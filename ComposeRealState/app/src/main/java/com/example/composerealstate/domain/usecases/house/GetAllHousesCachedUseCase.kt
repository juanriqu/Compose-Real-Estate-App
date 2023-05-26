package com.example.composerealstate.domain.usecases.house

import com.example.composerealstate.data.repository.HouseRepository
import javax.inject.Inject

class GetAllHousesCachedUseCase @Inject constructor(
    private val houseRepository: HouseRepository
) {
    /**
     * This function retrieves all houses from the local database.
     */
    suspend fun invoke() = houseRepository.getAllHousesCached()
}