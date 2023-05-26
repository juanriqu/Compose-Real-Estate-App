package com.example.composerealstate.network

import com.example.composerealstate.data.model.HouseResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * This interface is used to make API calls to a backend server
 */
interface HouseApiService {
    @GET("house")
    suspend fun getAllHouses(): Response<HouseResponse>
}