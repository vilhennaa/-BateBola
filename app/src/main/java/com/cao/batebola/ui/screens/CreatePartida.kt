package com.cao.batebola.ui.screens

import Routes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cao.batebola.dados.entity.Partida.Partida
import com.cao.batebola.ui.ViewModel.PartidaViewModel
import com.cao.batebola.ui.feature.addjogador.AddJogadorEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun CreatePartidaScreen(navController: NavController, viewModel: PartidaViewModel) {

    val coroutineScope = rememberCoroutineScope()

    var partidaTitulo by remember { mutableStateOf("") }
    var partidaTimeA by remember { mutableStateOf("") }
    var partidaTimeB by remember { mutableStateOf("") }
    var partidaData by remember { mutableStateOf("") }
    var partidaLocal by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(8.dp))
        // Botão de Voltar
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }

        Text(
            text = "Criar Nova Partida",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = partidaTitulo,
            onValueChange = { partidaTitulo = it },
            label = { Text("Título da Partida") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = partidaTimeA,
            onValueChange = { partidaTimeA = it },
            label = { Text("Nome time A:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = partidaTimeB,
            onValueChange = { partidaTimeB = it },
            label = { Text("Nome time B:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedTextField(
            value = partidaData,
            onValueChange = { partidaData = it },
            label = { Text("Data:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))

        OutlinedTextField(
            value = partidaLocal,
            onValueChange = { partidaLocal = it },
            label = { Text("Local:") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    val partidaSalvar = Partida(
                        titulo = partidaTitulo,
                        timeA = partidaTimeA,
                        timeB = partidaTimeB,
                        data = partidaData,
                        local = partidaLocal
                    )
                    viewModel.gravar(partidaSalvar)
                    navController.navigate(Routes.TELA_DOIS)

                }
            },
            modifier = Modifier.fillMaxWidth(),colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF04330A))
        ) {
            Text("Salvar Partida", fontSize = 18.sp)
        }
    }
}
