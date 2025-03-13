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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.foundation.*
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import br.com.ic2tech.trustterm.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import trustterm.composeapp.generated.resources.Res
import trustterm.composeapp.generated.resources.*

val hGlobalDrawer = CommonDrawerMenu()
class CommonDrawerMenu {

    @Composable
    fun DrawerContent(onItemClick: (NavigateTo) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .requiredWidth(300.dp)
                .verticalScroll(rememberScrollState())
        ) {
            IconButton(onClick = { onItemClick(NavigateTo.HIDE_MENU) },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 5.dp, top = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Fechar",
                    tint = Color(PANTONE_OFFWHITE),
                    modifier = Modifier
                        .size(25.dp)
                )
            }
            Image(
                painter = painterResource(Res.drawable.trust_term_light),
                contentDescription = "Logotipo",
                modifier =  Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.size(50.dp))
            Divider(color = Color(PANTONE_OFFWHITE))
            DrawerItem("Inicio", Res.drawable.home, NavigateTo.HOME, onItemClick)

            ExpandableMenu(title = "Certificações", resource = Res.drawable.article) {
                DrawerItem("Executar", Res.drawable.play_circle, NavigateTo.TEST_RUN, onItemClick)
                DrawerItem("Exportar", Res.drawable.upload, NavigateTo.NONE, onItemClick)
                DrawerItem("Importar", Res.drawable.download, NavigateTo.NONE, onItemClick)
            }

            ExpandableMenu(title = "Relatórios", resource = Res.drawable.flag) {
                DrawerItem("Detalhado", Res.drawable.insert_chart, NavigateTo.NONE, onItemClick)
                DrawerItem("Resumido", Res.drawable.summarize, NavigateTo.NONE, onItemClick)
            }

            ExpandableMenu(title = "Visualizar", resource = Res.drawable.visibility) {
                DrawerItem("Logs Serial", Res.drawable.cable, NavigateTo.NONE, onItemClick)
                DrawerItem("Logs Protocolo", Res.drawable.sync_alt, NavigateTo.NONE, onItemClick)
                DrawerItem("Logs Integração BTT", Res.drawable.credit_card, NavigateTo.NONE, onItemClick)
                DrawerItem("Logs Erro", Res.drawable.error, NavigateTo.NONE, onItemClick)
                DrawerItem("Mensagens Notify", Res.drawable.mark_unread_chat_alt, NavigateTo.NONE, onItemClick)
            }

            DrawerItem("Configurações", Res.drawable.settings, NavigateTo.NONE, onItemClick)
            DrawerItem("Chaves", Res.drawable.vpn_key, NavigateTo.NONE, onItemClick)

            ExpandableMenu(title = "Ferramentas", resource = Res.drawable.construction) {
                DrawerItem("CRC-16 Calculator", Res.drawable.calculate, NavigateTo.NONE, onItemClick)
                DrawerItem("Decrypt Message", Res.drawable.lock_open, NavigateTo.NONE, onItemClick)
                DrawerItem("EMV TLV Parser", Res.drawable.horizontal_split, NavigateTo.NONE, onItemClick)
                DrawerItem("Abecs Parser", Res.drawable.dashboard, NavigateTo.NONE, onItemClick)
            }

            DrawerItem("Sair", Res.drawable.logout, NavigateTo.NONE, onItemClick)
        }
    }

    @Composable
    fun DrawerItem(text: String, resource: DrawableResource, navigateTo: NavigateTo, onClick: (NavigateTo) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(navigateTo) })
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(resource),
                contentDescription = text,
                modifier = Modifier.padding(end = 16.dp),
                colorFilter = ColorFilter.tint(Color(PANTONE_OFFWHITE))
            )
            Text(
                text = text,
                color = Color(PANTONE_OFFWHITE),
                style = TextStyle(
                    fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            )
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
                Text(
                    text = title,
                    color = Color(PANTONE_OFFWHITE),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(resource = Res.font.OpenSans)),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
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

fun eFilterNavigateTo (eNavigateTo : NavigateTo) : Boolean {
    when(eNavigateTo){
        NavigateTo.HOME,
        NavigateTo.TEST_RUN -> {
            return true
        }
        NavigateTo.HIDE_MENU -> {
            println("apenas oculta o menu")
        }
        else -> {
            println("onNavigate nao implementado")
        }
    }
    return false
}