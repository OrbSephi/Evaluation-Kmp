package com.example.eval_d3_p3

interface Platform {
    // Platform name is used for basic diagnostics.
    val name: String
}

// expect/actual keeps platform-specific details out of common code.
expect fun getPlatform(): Platform