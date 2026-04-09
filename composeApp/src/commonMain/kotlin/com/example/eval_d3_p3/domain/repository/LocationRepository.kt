package com.example.eval_d3_p3.domain.repository

import com.example.eval_d3_p3.domain.model.Location

interface LocationRepository {
    // Domain contract for fetching location data.
    suspend fun getLocations(): List<Location>
    suspend fun getLocation(id: Int): Location
}
