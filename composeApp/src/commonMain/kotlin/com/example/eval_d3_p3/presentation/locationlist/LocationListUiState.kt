package com.example.eval_d3_p3.presentation.locationlist

import com.example.eval_d3_p3.domain.model.Location

data class LocationListUiState(
    // Single source of truth for the list screen.
    val isLoading: Boolean = false,
    val locations: List<Location> = emptyList(),
    val errorMessage: String? = null,
)
