package com.cao.batebola.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cao.batebola.ui.screens.utils.TopBarMin


@Composable
fun SecondScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = { TopBarMin(drawerState) },
        content = { padding -> Conteudo2(padding) },
        floatingActionButton = { FloatButton() },
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
private fun FloatButton() {
    FloatingActionButton(onClick = { }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "+"
        )
    }
}

@Composable
private fun Conteudo2(padding: PaddingValues) {
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
        Text(text = "tela 2")
    }
}


