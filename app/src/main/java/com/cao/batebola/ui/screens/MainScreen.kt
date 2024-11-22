package com.cao.batebola.ui.screens


import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle

import androidx.compose.material3.DrawerState

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.utils.TopBar

object TelaUm {
    val TELA_UM_A_ROUTE = "t1a"
    val TELA_UM_B_ROUTE = "t1b"
}

@Composable
fun MainScreen(drawerState: DrawerState) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = TelaUm.TELA_UM_A_ROUTE
            ) {
                composable(TelaUm.TELA_UM_A_ROUTE) {
                    LoginScreen(padding)
                }
                composable(TelaUm.TELA_UM_B_ROUTE) {
                    CadastroScreen(padding)
                }

            }
        },
        bottomBar = { BottomAppBarMinima(navController) }
    )
}

@Composable
private fun BottomAppBarMinima(navController: NavController) {

    NavigationBar(containerColor = Color(0xFF064D0C)) {
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(TelaUm.TELA_UM_A_ROUTE)
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "A",
                    modifier = Modifier.size(40.dp),
                    Color.White
                )
            },
            label = { Text(text = "LOGIN", color = Color.White) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                navController.navigate(TelaUm.TELA_UM_B_ROUTE)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "B",
                    modifier = Modifier.size(40.dp),
                    Color.White
                )
            },
            label = { Text(text = "CADASTRAR", color = Color.White) }
        )

    }
}

