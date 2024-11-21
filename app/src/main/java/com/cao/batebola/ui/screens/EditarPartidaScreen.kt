package com.cao.batebola.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.ui.ViewModel.PartidaViewModel
import com.cao.batebola.ui.screens.utils.TopBar
@Composable
fun EditarPartidaScreen(
    navController: NavHostController,
    viewModel: PartidaViewModel,
    partidaId: Int,
    drawerState: DrawerState
) {
    // Variável de estado para armazenar a partida
    var partida by remember { mutableStateOf<Partida?>(null) }

    // Usando LaunchedEffect para chamar a função suspend getPartidaById
    LaunchedEffect(partidaId) {
        partida = viewModel.getPartidaById(partidaId) // Chama a função suspend dentro do LaunchedEffect
    }

    // Se a partida não for encontrada, você pode exibir uma mensagem de erro ou voltar
    if (partida == null) {
        // Exiba uma mensagem de erro ou volte para a tela anterior
        return
    }

    var titulo by remember { mutableStateOf(partida?.titulo ?: "") }
    var timeA by remember { mutableStateOf(partida?.timeA ?: "") }
    var timeB by remember { mutableStateOf(partida?.timeB ?: "") }
    var data by remember { mutableStateOf(partida?.data ?: "") }
    var local by remember { mutableStateOf(partida?.local ?: "") }

    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = timeA,
                    onValueChange = { timeA = it },
                    label = { Text("Time A") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = timeB,
                    onValueChange = { timeB = it },
                    label = { Text("Time B") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = data,
                    onValueChange = { data = it },
                    label = { Text("Data") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = local,
                    onValueChange = { local = it },
                    label = { Text("Local") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        // Salva as alterações na partida
                        val partidaAtualizada = partida?.copy(
                            titulo = titulo,
                            timeA = timeA,
                            timeB = timeB,
                            data = data,
                            local = local
                        )
                        if (partidaAtualizada != null) {
                            viewModel.gravar(partidaAtualizada)
                            navController.popBackStack() // Volta para a tela de lista
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF064D0C))
                ) {
                    Text(text = "Salvar Alterações", color = Color.White)
                }
            }
        }
    )
}
