package br.com.ic2tech.trustterm.view

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import androidx.compose.foundation.*
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
fun TestScreen(onNavigate: (NavigateTo) -> Unit) {
    val hScaffoldState = rememberScaffoldState()
    val hScope = rememberCoroutineScope()

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
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(0.35f)
        ) { // Box Wellcome Message
            Text(
                text = "TELA DE TESTE",
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
    }
}
