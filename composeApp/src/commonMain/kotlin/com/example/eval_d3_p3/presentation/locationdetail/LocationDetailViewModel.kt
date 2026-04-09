package com.example.eval_d3_p3.presentation.locationdetail

import com.example.eval_d3_p3.domain.usecase.GetLocationDetailUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationDetailViewModel(
    private val getLocationDetail: GetLocationDetailUseCase,
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _state = MutableStateFlow(LocationDetailUiState())
    val state: StateFlow<LocationDetailUiState> = _state.asStateFlow()

    private var currentId: Int? = null

    fun onIntent(intent: LocationDetailIntent) {
        val id = currentId ?: return
        when (intent) {
            LocationDetailIntent.Refresh,
            LocationDetailIntent.Retry,
            -> loadLocation(id)
        }
    }

    fun loadLocation(id: Int) {
        // Guard to prevent overlapping requests.
        if (_state.value.isLoading) return

        currentId = id

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)
            runCatching { getLocationDetail(id) }
                .onSuccess { location ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        location = location,
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
