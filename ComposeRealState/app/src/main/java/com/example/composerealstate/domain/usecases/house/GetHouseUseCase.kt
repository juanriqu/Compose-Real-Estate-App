package com.example.composerealstate.domain.usecases.house

import com.example.composerealstate.data.repository.HouseRepository
import javax.inject.Inject

class GetHouseUseCase @Inject constructor(
    private val houseRepository: HouseRepository
) {
    operator fun invoke(houseID: Int) = houseRepository.getHouseCached(houseID)
}