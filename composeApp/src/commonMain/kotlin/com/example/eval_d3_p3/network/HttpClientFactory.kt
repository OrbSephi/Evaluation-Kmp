package com.example.eval_d3_p3.network

import io.ktor.client.HttpClient

// Factory is expect/actual to keep engine selection per platform.
expect fun createHttpClient(): HttpClient

