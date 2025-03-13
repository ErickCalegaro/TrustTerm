package br.com.ic2tech.trustterm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
//        state = rememberWindowState(WindowPlacement.Maximized),
        title = "TrustTerm by IC2Tech",
    ) {
        App()
    }
}