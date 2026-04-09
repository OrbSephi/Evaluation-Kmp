package com.example.eval_d3_p3.presentation.locationlist

sealed interface LocationListIntent {
    // User actions handled by the list view model.
    data object Refresh : LocationListIntent
    data object Retry : LocationListIntent
}
