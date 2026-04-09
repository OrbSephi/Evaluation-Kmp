package com.example.eval_d3_p3.platform

import android.content.Context
import android.media.MediaPlayer
import kotlin.random.Random

actual class SoundManager actual constructor(platformContext: PlatformContext?) {
    private val context = platformContext?.appContext as? Context

    actual fun playClick() {
        val safeContext = context ?: return
        val assetPath = soundAssetPaths.random(Random.Default)

        // Read mp3 from assets to avoid bundling platform-specific APIs in common code.
        safeContext.assets.openFd(assetPath).use { afd ->
            val player = MediaPlayer()
            player.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            player.setOnCompletionListener { it.release() }
            player.prepare()
            player.start()
        }
    }
}
