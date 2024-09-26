package com.cao.batebola.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.cao.batebola.ui.screens.utils.TopBar

@Composable
fun ThirdScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->

            val jogadores = listOf(
                Jogadores(
                    titulo = "Felipe",
                    descricao = "Atacante",
                    id = 1
                ),
                Jogadores(
                    titulo = "Manfré",
                    descricao = "Meio-campo",
                    id = 2
                ),
                Jogadores(
                    titulo = "Gustavo",
                    descricao = "Goleiro",
                    id = 3
                ),
                Jogadores(
                    titulo = "Joao",
                    descricao = "Zagueiro",
                    id = 4
                ),
                Jogadores(
                    titulo = "Paulinho",
                    descricao = "Volante",
                    id = 5
                ),
                Jogadores(
                    titulo = "Jhonathan",
                    descricao = "Meio-campo",
                    id = 6
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
                items(jogadores) { jogador -> // Atualizado para "jogadores"
                    JogadorCard(jogador) // Atualizado para "JogadorCard"
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        floatingActionButton = { FloatButtonThird() }
    )
}

@Composable
fun JogadorCard(jogador: Jogadores) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
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
                text = "Nome: ${jogador.titulo}",
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Posição: ${jogador.descricao}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "ID: ${jogador.id}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
@Composable
fun FloatButtonThird() {
    FloatingActionButton(
        onClick = { /*  */ },
        containerColor = Color(0xFF064D0C)
    ) {
        Icon(
            imageVector = Icons.Filled.Edit,
            contentDescription = "Adicionar tarefa",
            tint = Color.White
        )
    }
}

data class Jogadores(
    var titulo: String,
    var descricao: String,
    var id: Int? = null
)
