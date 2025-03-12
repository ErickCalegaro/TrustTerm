package br.com.ic2tech.trustterm.view

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
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import br.com.ic2tech.trustterm.*
import br.com.ic2tech.trustterm.utils.StringUtil
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import trustterm.composeapp.generated.resources.Res
import trustterm.composeapp.generated.resources.*

class MainScreen {
    @Composable
    @Preview
    fun MainScreen() {
        val hScaffoldState = rememberScaffoldState()
        val hScope = rememberCoroutineScope()
        val sWellcomeMessage = StringUtil().getWellcomeMessage()

        Scaffold(
            scaffoldState = hScaffoldState,
            topBar = {
                TopAppBar(
                    backgroundColor = Color(PANTONE_CUSTOM),
                    title = {
                        Text(
                            text = "TrustTerm",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color(PANTONE_OFFWHITE),
                            style = TextStyle(
                                fontFamily = FontFamily(Font(resource = Res.font.Roboto)),
                                fontWeight = FontWeight.Normal,
                                fontSize = 22.sp
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { hScope.launch { hScaffoldState.drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color(PANTONE_OFFWHITE)
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { println("LOGO") }) {
                            Image(
                                painter = painterResource(Res.drawable.trust_term_light),
                                contentDescription = "Logotipo",
                                modifier =  Modifier
                                    .size(50.dp)
                            )
                        }
                    }
                )
            },
            drawerContent = {
                DrawerContent {
                    hScope.launch { hScaffoldState.drawerState.close() }
                }
            },
            drawerBackgroundColor = Color(PANTONE_CUSTOM),
            drawerShape = NavShape(300.dp, 0f),
            drawerGesturesEnabled = true,
            drawerScrimColor = Color.Black.copy(alpha = 0.3f),
        ) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxHeight(0.35f)
                ) { // Box Wellcome Message
                    Text(
                        text = sWellcomeMessage,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center,
                        color = Color(PANTONE_OFFBLACK),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                            fontWeight = FontWeight.Normal,
                            fontSize = 65.sp
                        )
                    )
                }
                Box( // Box All Buttons
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(innerPadding)
                        .fillMaxHeight()
                        .fillMaxWidth()
                ) {
                    Column( // Column Btn 1
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .fillMaxWidth(0.5f)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth()
                        ){
                            Button(
                                onClick = { println("Executar") },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(0.85f)
                                    .fillMaxHeight(0.8f),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(Color(PANTONE_PASTEL_GREEN))
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.play_circle),
                                        contentDescription = "Executar",
                                        modifier =  Modifier.size(50.dp),
                                        colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
                                    )
                                    Text(
                                        text = "Executar",
                                        modifier = Modifier.padding(start = 10.dp),
                                        color = Color(PANTONE_OFFWHITE),
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 28.sp
                                        )
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ){
                            Button(
                                onClick = { println("Importar") },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(0.85f)
                                    .fillMaxHeight(0.8f),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(Color(PANTONE_PASTEL_BLUE))
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.download),
                                        contentDescription = "Importar",
                                        modifier =  Modifier.size(50.dp),
                                        colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
                                    )
                                    Text(
                                        text = "Importar",
                                        modifier = Modifier.padding(start = 10.dp),
                                        color = Color(PANTONE_OFFWHITE),
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 28.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Column( // Column Btn 2
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .fillMaxWidth(0.5f)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth()
                        ){
                            Button(
                                onClick = { println("Configuração") },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(0.85f)
                                    .fillMaxHeight(0.8f),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(Color(PANTONE_MIDDLE_GREY))
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.settings),
                                        contentDescription = "Configuração",
                                        modifier =  Modifier.size(50.dp),
                                        colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
                                    )
                                    Text(
                                        text = "Configuração",
                                        modifier = Modifier.padding(start = 10.dp),
                                        color = Color(PANTONE_OFFWHITE),
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 24.sp
                                        )
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .fillMaxHeight()
                                .fillMaxWidth()
                        ){
                            Button(
                                onClick = { println("Exportar") },
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .fillMaxWidth(0.85f)
                                    .fillMaxHeight(0.8f),
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(Color(PANTONE_PASTEL_ORANGE))
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Image(
                                        painter = painterResource(Res.drawable.upload),
                                        contentDescription = "Exportar",
                                        modifier =  Modifier.size(50.dp),
                                        colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
                                    )
                                    Text(
                                        text = "Exportar",
                                        modifier = Modifier.padding(start = 10.dp),
                                        color = Color(PANTONE_OFFWHITE),
                                        style = TextStyle(
                                            fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 28.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    class NavShape(
        private val iWidthOffset: Dp,
        private val fScale: Float
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
                            size.width * fScale + with(density) { iWidthOffset.toPx() },
                            size.height
                        )
                    ),
                    topRight = CornerRadius(4f, 4f),
                    bottomRight = CornerRadius(4f, 4f)
                )
            )
        }
    }

    @Composable
    fun DrawerContent(onItemClick: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(300.dp)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(Res.drawable.trust_term_light),
                contentDescription = "Logotipo",
                modifier =  Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(30.dp))
            Divider(color = Color(PANTONE_OFFWHITE))
            DrawerItem("Inicio", Icons.Outlined.Home, onItemClick)

            ExpandableMenu(title = "Certificações", resource = Res.drawable.article) {
                DrawerItem("Executar", Res.drawable.play_circle, onItemClick)
                DrawerItem("Exportar", Res.drawable.upload, onItemClick)
                DrawerItem("Importar", Res.drawable.download, onItemClick)
            }

            ExpandableMenu(title = "Relatórios", resource = Res.drawable.flag) {
                DrawerItem("Detalhado", Res.drawable.insert_chart, onItemClick)
                DrawerItem("Resumido", Res.drawable.summarize, onItemClick)
            }

            ExpandableMenu(title = "Visualizar", resource = Res.drawable.visibility) {
                DrawerItem("Logs Serial", Res.drawable.cable, onItemClick)
                DrawerItem("Logs Protocolo", Res.drawable.sync_alt, onItemClick)
                DrawerItem("Logs Integração BTT", Res.drawable.credit_card, onItemClick)
                DrawerItem("Logs Erro", Res.drawable.error, onItemClick)
                DrawerItem("Mensagens Notify", Res.drawable.mark_unread_chat_alt, onItemClick)
            }

            DrawerItem("Configurações", Icons.Outlined.Settings, onItemClick)
            DrawerItem("Chaves", Res.drawable.vpn_key, onItemClick)

            ExpandableMenu(title = "Ferramentas", resource = Res.drawable.construction) {
                DrawerItem("CRC-16 Calculator", Res.drawable.calculate) { println("CRC-16 Calculator") }
                DrawerItem("Decrypt Message", Res.drawable.lock_open) { println("Decrypt Message") }
                DrawerItem("EMV TLV Parser", Res.drawable.horizontal_split) { println("EMV TLV Parser") }
                DrawerItem("Abecs Parser", Res.drawable.dashboard) { println("Abecs Parser") }
            }

            DrawerItem("Sair", Res.drawable.logout, onItemClick)
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
            Icon(icon,
                contentDescription = text,
                modifier = Modifier.padding(end = 16.dp),
                tint = Color(PANTONE_OFFWHITE)
            )
            Text(text, color = Color(PANTONE_OFFWHITE))
        }
    }

    @Composable
    fun DrawerItem(text: String, resource: DrawableResource, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(resource),
                contentDescription = text,
                modifier = Modifier.padding(end = 16.dp),
                colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
            )
            Text(text, color = Color(PANTONE_OFFWHITE))
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
                Icon(icon,
                    contentDescription = title,
                    modifier = Modifier.padding(end = 16.dp),
                    tint = Color(PANTONE_OFFWHITE)
                )
                Text(title, color = Color(PANTONE_OFFWHITE))
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = Color(PANTONE_OFFWHITE)
                )
            }
            if (expanded) {
                Column(modifier = Modifier.padding(start = 24.dp)) {
                    content()
                }
            }
        }
    }

    @Composable
    fun ExpandableMenu(title: String, resource: DrawableResource, content: @Composable ColumnScope.() -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(resource),
                    contentDescription = title,
                    modifier = Modifier.padding(end = 16.dp),
                    colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
                )
                Text(title, color = Color(PANTONE_OFFWHITE))
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Expandir",
                    tint = Color(PANTONE_OFFWHITE)
                )
            }
            if (expanded) {
                Column(modifier = Modifier.padding(start = 24.dp)) {
                    content()
                }
            }
        }
    }
}
