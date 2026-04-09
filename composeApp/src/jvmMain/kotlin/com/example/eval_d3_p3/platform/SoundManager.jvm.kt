package com.example.eval_d3_p3.platform

import javazoom.jl.player.Player

actual class SoundManager actual constructor(platformContext: PlatformContext?) {
    actual fun playClick() {
        val assetPath = soundAssetPaths.random()
        val stream = SoundManager::class.java.classLoader?.getResourceAsStream(assetPath) ?: return

        // Desktop playback uses a background thread to avoid blocking UI.
        Thread {
            stream.use { Player(it).play() }
        }.start()
    }
}
