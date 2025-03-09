package br.com.ic2tech.trustterm.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch

@Composable
fun MyDrawerApp() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Minha App") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent { scope.launch { scaffoldState.drawerState.close() } }
        },
        drawerShape = NavShape(300.dp, 0f),
        drawerGesturesEnabled = true,
        drawerScrimColor = Color.Black.copy(alpha = 0.3f),
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("Conteúdo principal aqui", modifier = Modifier.padding(16.dp))
        }
    }
}

class NavShape(
    private val widthOffset: Dp,
    private val scale: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rounded(
            RoundRect(
                rect = Rect(
                    Offset.Zero,
                    Offset(
                        size.width * scale + with(density) { widthOffset.toPx() },
                        size.height
                    )
                ),
                topRight = CornerRadius(15f, 15f),
                bottomRight = CornerRadius(15f, 15f)
            )
        )
    }
}

@Composable
fun DrawerContent(onItemClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .requiredWidth(300.dp) // Define o menu com 300px de largura
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text("Menu", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Divider()
        DrawerItem("Home", Icons.Default.Home, onItemClick)

        ExpandableMenu(title = "Configurações", icon = Icons.Default.Settings) {
            DrawerItem("Perfil", Icons.Default.Person, onItemClick)
            DrawerItem("Notificações", Icons.Default.Notifications, onItemClick)
        }

        DrawerItem("Sair", Icons.Default.ExitToApp, onItemClick)
    }
}

@Composable
fun DrawerItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = text, modifier = Modifier.padding(end = 16.dp))
        Text(text)
    }
}

@Composable
fun ExpandableMenu(title: String, icon: ImageVector, content: @Composable ColumnScope.() -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.padding(end = 16.dp))
            Text(title, fontWeight = FontWeight.Bold)
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expandir"
            )
        }
        if (expanded) {
            Column(modifier = Modifier.padding(start = 24.dp)) {
                content()
            }
        }
    }
}
