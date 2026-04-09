package com.example.eval_d3_p3.platform

expect class SoundManager(platformContext: PlatformContext? = null) {
    fun playClick()
}

internal val soundAssetPaths = listOf(
    // Shared sound paths for both Android assets and desktop resources.
    "sound/Voicy_Pickle Rick!!!.mp3",
    "sound/Voicy_Rick and Morty - Riggity Riggity Wrecked Son!.mp3",
    "sound/Voicy_Wubba Lubba Dub Dub (rick and morty).mp3",
)
