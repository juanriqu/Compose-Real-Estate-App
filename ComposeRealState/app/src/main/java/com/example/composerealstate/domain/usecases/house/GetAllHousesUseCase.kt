package com.example.composerealstate.domain.usecases.house

import com.example.composerealstate.data.repository.HouseRepository
import javax.inject.Inject

class GetAllHousesUseCase @Inject constructor(
    private val houseRepository: HouseRepository
) {
    operator fun invoke() = houseRepository.getAllHouses()
}