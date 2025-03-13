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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import br.com.ic2tech.trustterm.*
import br.com.ic2tech.trustterm.utils.StringUtil
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import trustterm.composeapp.generated.resources.Res
import trustterm.composeapp.generated.resources.*

@Composable
@Preview
fun HomeScreen(onNavigate: (NavigateTo) -> Unit) {
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
                    IconButton(onClick = { println("ButtonMenuClicked") ; hScope.launch { hScaffoldState.drawerState.open() } }) {
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
            hGlobalDrawer.DrawerContent { eNavigateTo ->
                hScope.launch { hScaffoldState.drawerState.close() }
                println("eNavigateTo = [$eNavigateTo]")
                if (eFilterNavigateTo(eNavigateTo)){
                    onNavigate(eNavigateTo)
                }
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
