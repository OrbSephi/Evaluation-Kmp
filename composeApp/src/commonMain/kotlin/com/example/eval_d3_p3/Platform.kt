package com.example.eval_d3_p3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform