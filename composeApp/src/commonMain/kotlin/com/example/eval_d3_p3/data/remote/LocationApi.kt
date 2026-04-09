package com.example.eval_d3_p3.data.remote

import com.example.eval_d3_p3.data.remote.dto.LocationsResponseDto
import com.example.eval_d3_p3.data.remote.dto.LocationDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class LocationApi(
    private val client: HttpClient,
    private val baseUrl: String = "https://rickandmortyapi.com/api",
) {
    // Remote calls are kept here to isolate networking from repositories.
    suspend fun getLocations(): LocationsResponseDto {
        return client.get("$baseUrl/location").body()
    }

    suspend fun getLocation(id: Int): LocationDto {
        return client.get("$baseUrl/location/$id").body()
    }
}
