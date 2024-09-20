package com.cao.batebola

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.MainScreen
import com.cao.batebola.ui.screens.SecondScreen
import com.cao.batebola.ui.screens.ThirdScreen
import kotlinx.coroutines.launch

object BateBotaRotas {
    const val TELA_ROTA_UM = "tela_um"
    const val TELA_ROTA_DOIS = "tela_dois"
    const val TELA_ROTA_TRES = "tela_tres"
}

@Preview
@Composable
fun BateBolaApp() {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
            },
        content = {
            NavHost(
                navController = navController,
                startDestination = BateBotaRotas.TELA_ROTA_UM
            ) {
                composable(BateBotaRotas.TELA_ROTA_UM){
                    MainScreen(drawerState)
                }
                composable(BateBotaRotas.TELA_ROTA_DOIS){
                    SecondScreen(drawerState)
                }
                composable(BateBotaRotas.TELA_ROTA_TRES){
                    ThirdScreen(drawerState)
                }
            }
        }

    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBack?.destination?.route ?: BateBotaRotas.TELA_ROTA_UM

    val ehRotaUm = currentRoute == BateBotaRotas.TELA_ROTA_UM
    val ehRotaDois = currentRoute == BateBotaRotas.TELA_ROTA_DOIS
    val ehRotaTres = currentRoute == BateBotaRotas.TELA_ROTA_TRES

    Column(
        modifier = Modifier
            .width(300.dp)
            .background(Color.White)
            .fillMaxHeight()
            .padding(30.dp)
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        TextButton(
            onClick = {
                navController.navigate(BateBotaRotas.TELA_ROTA_UM)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaUm)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaUm)
            )
            Text(
                text = "Tela Principal",
                fontSize = (30.sp),
                color = getColorTexto(ehRotaUm)
            )
        }

        TextButton(
            onClick = {
                navController.navigate(BateBotaRotas.TELA_ROTA_DOIS)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaDois)
            )
            Text(
                text = "Tela 2",
                fontSize = (30.sp),
                color = getColorTexto(ehRotaDois)
            )
        }

        TextButton(
            onClick = {
                navController.navigate(BateBotaRotas.TELA_ROTA_TRES)
                coroutineScope.launch {
                    drawerState.close()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.size(40.dp),
                tint = getColorTexto(ehRotaTres)
            )
            Text(
                text = "Tela 3",
                fontSize = (30.sp),
                color = getColorTexto(ehRotaTres)
            )
        }
    }
}

fun getColorMenu(estaSelecionada: Boolean): Color {
    if (estaSelecionada){
        return Color.Yellow
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


