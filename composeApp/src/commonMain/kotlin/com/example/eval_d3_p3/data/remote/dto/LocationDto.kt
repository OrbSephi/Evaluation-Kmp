package com.example.eval_d3_p3.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    // DTO mirrors the API response schema.
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
)

@Serializable
data class LocationsResponseDto(
    @SerialName("results") val results: List<LocationDto>,
)
