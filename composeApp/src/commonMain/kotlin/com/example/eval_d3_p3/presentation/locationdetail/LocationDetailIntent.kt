package com.example.eval_d3_p3.presentation.locationdetail

sealed interface LocationDetailIntent {
    // User actions handled by the detail view model.
    data object Retry : LocationDetailIntent
    data object Refresh : LocationDetailIntent
}
