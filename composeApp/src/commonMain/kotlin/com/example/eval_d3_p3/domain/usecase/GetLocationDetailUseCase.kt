package com.example.eval_d3_p3.domain.usecase

import com.example.eval_d3_p3.domain.repository.LocationRepository

class GetLocationDetailUseCase(
    private val repository: LocationRepository,
) {
    // Use case isolates detail retrieval from UI.
    suspend operator fun invoke(id: Int) = repository.getLocation(id)
}
