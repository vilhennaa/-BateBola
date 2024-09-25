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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.compose.rememberNavController
import com.cao.batebola.ui.screens.utils.TopBar


@Composable
fun SecondScreeen(drawerState: DrawerState) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->

            val times = listOf(
                Times(
                    titulo = "Time do Felipe",
                    descricao = "Profissional",
                    id = 1
                ),
                Times(
                    titulo = "Time do Manfré",
                    descricao = "Semi-Profissional",
                    id = 2
                ),
                Times(
                    titulo = "Time do Gustavo",
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
                items(times) { time ->
                    AfazerCard(time)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        floatingActionButton = { FloatButton() }
    )
}

@Composable
fun AfazerCard(times: Times) {
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
                text = "Título: ${times.titulo}",
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Descrição: ${times.descricao}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "ID: ${times.id}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FloatButton() {
    FloatingActionButton(
        onClick = { /*  */ },
        containerColor = Color(0xFF064D0C)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar tarefa",
            tint = Color.White
        )
    }
}


data class Times(
    var titulo: String,
    var descricao: String,
    var id: Int? = null
)