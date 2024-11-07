package com.cao.batebola.ui.screens


import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

import com.cao.batebola.ui.screens.utils.TopBar
@Composable
fun SecondScreeen(navController : NavHostController,  drawerState :DrawerState) {
    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->
            val partidas = listOf(
                Partida(
                    titulo = "Partida de fut",
                    descricao = "Profissional",
                    id = 1
                ),
                Partida(
                    titulo = "Futebol dos guri",
                    descricao = "Semi-Profissional",
                    id = 2
                ),
                Partida(
                    titulo = "Pelada da piazada",
                    descricao = "Amador",
                    id = 3
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(partidas) { partida ->
                    PartidaCard(partida)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        floatingActionButton = { FloatButton(navController) }
    )
}


@Composable
fun PartidaCard(partida: Partida) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Título: ${partida.titulo}",
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Descrição: ${partida.descricao}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = { /* TODO */ },
                modifier = Modifier
                    .width(140.dp)
                    .height(40.dp),
                shape = RectangleShape,colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF04330A))
            ) {
                Text(text = "Aceitar duelo")
            }


        }
    }
}

@Composable
fun FloatButton(navController: NavController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("create_team")
        },
        containerColor = Color(0xFF064D0C)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar tarefa",
            tint = Color.White
        )
    }
}



data class Partida(
    var titulo: String,
    var descricao: String,
    var concluido: Boolean = false,
    var id: Int? = null
)