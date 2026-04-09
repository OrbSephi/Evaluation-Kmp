package com.example.eval_d3_p3.data.local

import com.example.eval_d3_p3.domain.model.Location

class InMemoryLocationCache {
    // Simple in-memory cache to avoid repeated network calls.
    private var cached: List<Location> = emptyList()

    fun get(): List<Location> = cached

    fun getById(id: Int): Location? = cached.firstOrNull { it.id == id }

    fun putAll(locations: List<Location>) {
        cached = locations
    }

    fun putOne(location: Location) {
        cached = cached.filterNot { it.id == location.id } + location
    }
}
