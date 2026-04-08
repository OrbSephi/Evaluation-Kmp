package com.example.eval_d3_p3

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Evald3p3",
    ) {
        App()
    }
}