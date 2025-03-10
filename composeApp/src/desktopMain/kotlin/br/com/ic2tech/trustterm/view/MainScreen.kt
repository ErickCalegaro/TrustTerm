package br.com.ic2tech.trustterm.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.Icon
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
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.automirrored.outlined.*
import androidx.compose.material.icons.outlined.*

@Composable
@Preview
fun MyDrawerApp() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("TrustTerm") },
                navigationIcon = {
                    IconButton(onClick = { scope.launch { scaffoldState.drawerState.open() } }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            try {
                DrawerContent { scope.launch { scaffoldState.drawerState.close() } }
            }catch (e: Exception){
                e.printStackTrace()
            }
        },
        drawerShape = NavShape(300.dp, 0f),
        drawerGesturesEnabled = true,
        drawerScrimColor = Color.Black.copy(alpha = 0.3f),
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("Boa noite, Erick!", modifier = Modifier.padding(16.dp))
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
            .verticalScroll(rememberScrollState())
    ) {
        Text("Menu", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Divider()
        DrawerItem("Inicio", Icons.Outlined.Home) { println("Inicio") }

        ExpandableMenu(title = "Certificações", icon = Icons.AutoMirrored.Outlined.Article) {
            DrawerItem("Executar", Icons.Outlined.PlayCircle) { println("Executar") }
            DrawerItem("Exportar", Icons.Outlined.Upload) { println("Exportar") }
            DrawerItem("Importar", Icons.Outlined.Download) { println("Importar") }
        }

        ExpandableMenu(title = "Relatórios", icon = Icons.Outlined.Flag) {
            DrawerItem("Detalhado", Icons.Outlined.InsertChart) { println("Detalhado") }
            DrawerItem("Resumido", Icons.Outlined.Summarize) { println("Resumido") }
        }

        ExpandableMenu(title = "Visualizar", icon = Icons.Outlined.Visibility) {
            DrawerItem("Logs Serial", Icons.Outlined.Cable) { println("Logs Serial") }
            DrawerItem("Logs Protocolo", Icons.Outlined.SyncAlt) { println("Logs Protocolo") }
            DrawerItem("Logs Integração BTT", Icons.Outlined.CreditCard) { println("Logs Integração BTT") }
            DrawerItem("Logs Erro", Icons.Outlined.Error) { println("Logs Erro") }
            DrawerItem("Mensagens Notify", Icons.Outlined.MarkUnreadChatAlt) { println("Mensagens Notify") }
        }

        DrawerItem("Configurações", Icons.Outlined.Settings) { println("Configurações") }
        DrawerItem("Chaves", Icons.Outlined.VpnKey) { println("Chaves") }

        ExpandableMenu(title = "Ferramentas", icon = Icons.Outlined.Construction) {
            DrawerItem("CRC-16 Calculator", Icons.Outlined.Calculate) { println("CRC-16 Calculator") }
            DrawerItem("Decrypt Message", Icons.Outlined.LockOpen) { println("Decrypt Message") }
            DrawerItem("EMV TLV Parser", Icons.Outlined.HorizontalSplit) { println("EMV TLV Parser") }
            DrawerItem("Abecs Parser", Icons.Outlined.Dashboard) { println("Abecs Parser") }
        }

        DrawerItem("Sair", Icons.AutoMirrored.Outlined.Logout) { println("Sair") }
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
