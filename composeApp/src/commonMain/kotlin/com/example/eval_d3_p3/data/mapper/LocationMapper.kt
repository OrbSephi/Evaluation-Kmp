package com.example.eval_d3_p3.data.mapper

import com.example.eval_d3_p3.data.remote.dto.LocationDto
import com.example.eval_d3_p3.domain.model.Location

fun LocationDto.toDomain(): Location {
    // Map API DTO to domain model with derived resident count.
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residentCount = residents.size,
    )
}
