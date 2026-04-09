package com.example.eval_d3_p3.platform

actual class PlatformContext actual constructor(
    // Wrap Context so DI can pass it through expect/actual APIs.
    actual val appContext: Any,
)
