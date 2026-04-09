package com.example.eval_d3_p3.data.repository

import com.example.eval_d3_p3.data.local.InMemoryLocationCache
import com.example.eval_d3_p3.data.mapper.toDomain
import com.example.eval_d3_p3.data.remote.LocationApi
import com.example.eval_d3_p3.domain.model.Location
import com.example.eval_d3_p3.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val api: LocationApi,
    private val cache: InMemoryLocationCache,
) : LocationRepository {
    override suspend fun getLocations(): List<Location> {
        // Cache-first strategy to reduce API calls.
        val cached = cache.get()
        if (cached.isNotEmpty()) {
            return cached
        }

        val remote = api.getLocations().results.map { it.toDomain() }
        cache.putAll(remote)
        return remote
    }

    override suspend fun getLocation(id: Int): Location {
        val cached = cache.getById(id)
        if (cached != null) {
            return cached
        }

        val remote = api.getLocation(id).toDomain()
        cache.putOne(remote)
        return remote
    }
}
