package com.example.eval_d3_p3.platform

expect class PlatformContext(appContext: Any) {
    // Wrap platform-native context for DI and expect/actual APIs.
    val appContext: Any
}
