package com.example.eval_d3_p3.domain.usecase

import com.example.eval_d3_p3.domain.repository.LocationRepository

class GetLocationsUseCase(
    private val repository: LocationRepository,
) {
    // Use case isolates the list retrieval rule from UI.
    suspend operator fun invoke() = repository.getLocations()
}
