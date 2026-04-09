package com.example.eval_d3_p3

import android.os.Build

class AndroidPlatform : Platform {
    // Android platform name uses the SDK level for quick diagnostics.
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()