package br.com.ic2tech.trustterm.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.unit.*
import androidx.compose.foundation.*
import br.com.ic2tech.trustterm.*
import trustterm.composeapp.generated.resources.*


@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf(NavigateTo.HOME) }

    when (currentScreen) {
        NavigateTo.HOME -> HomeScreen { currentScreen = it }
        NavigateTo.TEST_RUN -> TestScreen { currentScreen = it }
        else -> {
            println("currentScreen nao existe")
        }
    }
}