package br.com.ic2tech.trustterm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TrustTerm",
    ) {
        App()
    }
}