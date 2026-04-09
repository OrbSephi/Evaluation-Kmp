package com.example.eval_d3_p3.domain.model

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    // Derived data from residents for UI display.
    val residentCount: Int,
)
