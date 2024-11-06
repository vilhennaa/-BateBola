package com.cao.batebola.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.CreateTeamScreen
import com.cao.batebola.ui.screens.MainScreen
import com.cao.batebola.ui.screens.ProfileScreen
import com.cao.batebola.ui.screens.SecondScreeen
import com.cao.batebola.ui.screens.ThirdScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import kotlinx.coroutines.launch

@Composable
fun BateBolaNavHost() {
    val navController = rememberNavController() // Criando o NavController
    val drawerState = rememberDrawerState(DrawerValue.Closed) // DrawerState para o menu lateral

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController = navController, drawerState = drawerState) // Passando o NavController aqui
        },
        content = {
            NavHost(
                navController = navController, // Passando o NavController aqui também
                startDestination = BateBotaRoutes.TELA_DOIS_ROTA
            ) {
                composable(BateBotaRoutes.TELA_UM_ROTA) {
                    MainScreen(drawerState)
                }
                composable(BateBotaRoutes.TELA_DOIS_ROTA) {
                    SecondScreeen(navController,drawerState)
                }
                composable(BateBotaRoutes.TELA_TRES_ROTA) {
                    ThirdScreen(drawerState)
                }
                composable(BateBotaRoutes.TELA_PERFIL_ROTA) {
                    ProfileScreen(drawerState)
                }

                // Adicionando a rota para a tela de criação de time
                composable("create_team") {
                    CreateTeamScreen(navController) // Agora o NavController é passado corretamente
                }
            }
        }
    )

}



object BateBotaRoutes {
    val TELA_UM_ROTA = "tela_um"
    val TELA_DOIS_ROTA = "tela_dois"
    val TELA_TRES_ROTA = "tela_tres"
    val TELA_PERFIL_ROTA = "tela_perfil"
}

@Preview(
    device = Devices.PIXEL
)
@Composable
fun BateBolaApp() {

}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: BateBotaRoutes.TELA_DOIS_ROTA

    val ehRotaUm = rotaAtual == BateBotaRoutes.TELA_UM_ROTA
    val ehRotaDois = rotaAtual == BateBotaRoutes.TELA_DOIS_ROTA
    val ehRotaTres = rotaAtual == BateBotaRoutes.TELA_TRES_ROTA
    val ehRotaPerfil = rotaAtual == BateBotaRoutes.TELA_PERFIL_ROTA

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))



        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(BateBotaRoutes.TELA_DOIS_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {

            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "b",
                modifier = Modifier.size(40.dp),
                Color.Black
            )

            Text(
                text = "Times cadastrados",
                fontSize = 30.sp,

                color = getColorTexto(ehRotaDois)
            )
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(BateBotaRoutes.TELA_UM_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "b",
                modifier = Modifier.size(40.dp),
                Color.Black
            )
            Text(text = "Login/Cadastro", fontSize = 25.sp,
                color = getColorTexto(ehRotaUm))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            ),
            onClick = {
                navController.navigate(BateBotaRoutes.TELA_TRES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "b",
                modifier = Modifier.size(40.dp),
                Color.Black
            )
            Text(text = "Seu time", fontSize = 30.sp,
                color = getColorTexto(ehRotaTres))
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaPerfil)
            ),
            onClick = {
                navController.navigate(BateBotaRoutes.TELA_PERFIL_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "b",
                modifier = Modifier.size(40.dp),
                Color.Black
            )
            Text(text = "Perfil", fontSize = 30.sp,
                color = getColorTexto(ehRotaPerfil))
        }
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaPerfil)
            ),
            onClick = {
                navController.navigate(BateBotaRoutes.TELA_PERFIL_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "b",
                modifier = Modifier.size(40.dp),
                Color.Black
            )
            Text(text = "Agenda", fontSize = 30.sp,
                color = getColorTexto(ehRotaPerfil))
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color(0xFF064D0C)
    } else {
        return Color.Transparent
    }
}

fun getColorTexto(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Black
    } else {
        return Color.DarkGray
    }
}
