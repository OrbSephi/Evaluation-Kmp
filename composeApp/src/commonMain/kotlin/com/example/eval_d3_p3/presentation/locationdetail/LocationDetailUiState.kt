package com.example.eval_d3_p3.presentation.locationdetail

import com.example.eval_d3_p3.domain.model.Location

data class LocationDetailUiState(
    // Single source of truth for the detail screen.
    val isLoading: Boolean = false,
    val location: Location? = null,
    val errorMessage: String? = null,
)
