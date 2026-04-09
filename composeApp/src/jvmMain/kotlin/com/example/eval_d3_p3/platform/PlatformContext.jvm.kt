package com.example.eval_d3_p3.platform

actual class PlatformContext actual constructor(
    // JVM does not require a native context, keep a placeholder for DI symmetry.
    actual val appContext: Any,
)
