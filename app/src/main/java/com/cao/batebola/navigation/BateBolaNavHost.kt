package com.cao.batebola.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cao.batebola.ui.screens.BateBolaApp
import com.cao.batebola.ui.screens.MainScreen
import com.cao.batebola.ui.screens.ThirdScreen
import kotlinx.serialization.Serializable


@Serializable
object BateBolaRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Composable
fun BateBolaNavHost() {
    val navController = rememberNavController()

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed)

    NavHost(
        navController = navController,
        startDestination = BateBolaRoute
    ) {
        composable<BateBolaRoute> {
            BateBolaApp()
        }

        composable<AddEditRoute> { backStackEntry ->
            val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            ThirdScreen(drawerState)
        }
    }
}