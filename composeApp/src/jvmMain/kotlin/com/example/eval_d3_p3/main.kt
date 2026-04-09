package com.example.eval_d3_p3

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.eval_d3_p3.di.initKoin

fun main() {
    // Desktop entrypoint initializes DI before showing the window.
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Evald3p3",
        ) {
            DesktopApp()
        }
    }
}