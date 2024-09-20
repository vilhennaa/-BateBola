package com.cao.batebola.ui.screens

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.utils.TopBarMin

object MainScreen {
    val TELA_ROTA_UM_X = "tela_um_x"
    val TELA_ROTA_UM_Y = "tela_um_y"
    val TELA_ROTA_UM_Z = "tela_um_z"
}

@Composable
fun MainScreen(drawerState: DrawerState) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBarMin(drawerState) },
        content = { padding ->
            Conteudo(padding) },
        floatingActionButton = { BottonAdd() },
        bottomBar = { BottonBarMin() }
    )
}

@Composable
private fun BottonBarMin() {
    BottomAppBar(
        containerColor = Color.Yellow
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
            Icon(
                imageVector = Icons.Default.Clear,
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Composable
private fun BottonAdd() {
    FloatingActionButton(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "add"
        )
    }
}

@Composable
private fun Conteudo(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bate Bola",
            modifier = Modifier
                .padding(padding),
            fontSize = 50.sp
        )
        Text(text = "tela principal")
    }
}

