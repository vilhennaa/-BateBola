package com.cao.batebola.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import com.cao.batebola.R
import com.cao.batebola.ui.screens.MainScreen
import com.cao.batebola.ui.screens.ThirdScreen
import kotlinx.coroutines.launch

object PlannerRotas {
    val TELA_UM_ROTA = "tela_um"
    val TELA_DOIS_ROTA = "tela_dois"
    val TELA_TRES_ROTA = "tela_tres"
}


@Preview(
    device = Devices.PIXEL
)
@Composable
fun BateBolaApp(){

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState)
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = PlannerRotas.TELA_UM_ROTA)
            {
                composable(PlannerRotas.TELA_UM_ROTA) {
                    MainScreen(drawerState)
                }
                composable(PlannerRotas.TELA_DOIS_ROTA) {
                    SecondScreeen(drawerState)
                }
                composable(PlannerRotas.TELA_TRES_ROTA) {
                    ThirdScreen(drawerState)
                }
            }
        }
    )
}

@Composable
private fun DrawerContent(
    navController: NavController,
    drawerState: DrawerState
) {

    val coroutineScope = rememberCoroutineScope()

    val currentBack by navController.currentBackStackEntryAsState()
    val rotaAtual = currentBack?.destination?.route ?: PlannerRotas.TELA_UM_ROTA

    val ehRotaUm = rotaAtual == PlannerRotas.TELA_UM_ROTA
    val ehRotaDois = rotaAtual == PlannerRotas.TELA_DOIS_ROTA
    val ehRotaTres = rotaAtual == PlannerRotas.TELA_TRES_ROTA

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
                containerColor = getColorMenu(ehRotaUm)
            ),
            onClick = {
                navController.navigate(PlannerRotas.TELA_UM_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "b",
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Login", fontSize = 30.sp,
                color = getColorTexto(ehRotaUm))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaDois)
            ),
            onClick = {
                navController.navigate(PlannerRotas.TELA_DOIS_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {

            Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "b",
                    modifier = Modifier.size(40.dp)
            )

            Text(text = "Times Cadastrados", fontSize = 30.sp,
                color = getColorTexto(ehRotaDois))
        }

        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorMenu(ehRotaTres)
            ),
            onClick = {
                navController.navigate(PlannerRotas.TELA_TRES_ROTA)
                coroutineScope.launch {
                    drawerState.close()
                }
            }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "b",
                modifier = Modifier.size(40.dp)
            )
            Text(text = "Tela 3", fontSize = 30.sp,
                color = getColorTexto(ehRotaTres))
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