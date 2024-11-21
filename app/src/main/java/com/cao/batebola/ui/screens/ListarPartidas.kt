package com.cao.batebola.ui.screens

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.ui.ViewModel.PartidaViewModel
import com.cao.batebola.ui.screens.utils.TopBar
import java.sql.Date

@Composable
fun ListarPartidas(navController : NavHostController, drawerState: DrawerState, viewModel: PartidaViewModel) {
    val partidas by viewModel.partidas.collectAsState()

    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                contentPadding = PaddingValues(16.dp)
            ) {
                items(partidas) { partida ->
                    PartidaCard(
                        partida = partida,
                        onEditClick = {
                            // Navega para a tela de edição, passando os dados da partida
                            navController.navigate("editar_partida/${partida.id}")
                        },
                        onDeleteClick = {
                            // Confirmação e exclusão da partida
                            viewModel.excluir(partida)
                        })
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        },
        floatingActionButton = { FloatButton(navController) }
    )
}
@Composable
fun PartidaCard(partida: Partida, onEditClick: (Partida) -> Unit, onDeleteClick: (Partida) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth() // Defina um tamanho específico para evitar altura infinita
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column( // Substitua LazyColumn por Column
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "${partida.titulo}",
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Time A: ${partida.timeA}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Time B: ${partida.timeB}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Data: ${partida.data}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
            Text(
                text = "Local: ${partida.local}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            // Ícones de Editar e Excluir
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Ícone de Editar
                IconButton(
                    onClick = { onEditClick(partida) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Editar",
                        tint = Color.Black
                    )
                }

                // Ícone de Excluir
                IconButton(
                    onClick = { onDeleteClick(partida) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Excluir",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}



@Composable
fun FloatButton(navController: NavController) {
    FloatingActionButton(
        onClick = { navController.navigate("createPartida") },
        containerColor = Color(0xFF064D0C)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar partida",
            tint = Color.White
        )
    }
}
