package com.example.eval_d3_p3.presentation.locationlist

import com.example.eval_d3_p3.domain.usecase.GetLocationsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationListViewModel(
    private val getLocations: GetLocationsUseCase,
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(LocationListUiState())
    val state: StateFlow<LocationListUiState> = _state.asStateFlow()

    fun onIntent(intent: LocationListIntent) {
        when (intent) {
            LocationListIntent.Refresh,
            LocationListIntent.Retry,
            -> loadLocations()
        }
    }

    fun loadLocations() {
        // Guard to prevent overlapping requests.
        if (_state.value.isLoading) return

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)
            runCatching { getLocations() }
                .onSuccess { locations ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        locations = locations,
                        errorMessage = null,
                    )
                }
                .onFailure { throwable ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = throwable.message ?: "Unknown error",
                    )
                }
        }
    }
}
