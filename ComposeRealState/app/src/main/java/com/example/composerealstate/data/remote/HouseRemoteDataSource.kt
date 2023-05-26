package com.example.composerealstate.data.remote

import com.example.composerealstate.data.model.toHouse
import com.example.composerealstate.domain.model.House
import com.example.composerealstate.network.HouseApiService
import com.example.composerealstate.network.NetworkResult
import javax.inject.Inject

/**
 * Fetches data from remote source, calling the generic [BaseAPIResponse.safeApiCall] function.
 */
class HouseRemoteDataSource @Inject constructor(
    private val houseApiService: HouseApiService
) : BaseAPIResponse() {
    suspend fun fetchHouses(): NetworkResult<List<House>> {
        return safeApiCall(apiCall = { houseApiService.getAllHouses() },
            transform = { housesResponse -> housesResponse.map { it.toHouse() } })
    }
}

