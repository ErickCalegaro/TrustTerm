package br.com.ic2tech.trustterm.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.ic2tech.trustterm.controller.GuideController
import br.com.ic2tech.trustterm.model.GuideType
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun GuideListScreen(controller: GuideController) {
    var guides by remember { mutableStateOf(emptyList<GuideType>()) }

    LaunchedEffect(Unit) {
        guides = controller.fetchGuides()
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Roteiros", style = MaterialTheme.typography.h4)

        LazyColumn {
            items(guides) { guide ->
                GuideItem(guide)
            }
        }
    }
}

@Composable
fun GuideItem(guide: GuideType) {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(guide.sName, style = MaterialTheme.typography.body1)
            Text("by ${guide.sId}", style = MaterialTheme.typography.body2)
        }
    }
}