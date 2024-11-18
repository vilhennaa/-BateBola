package com.cao.batebola.ui.feature.addjogador

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cao.batebola.dados.BateBolaDatabaseProvider
import com.cao.batebola.dados.repository.Jogador.JogadorRepositoryImpl
import com.cao.batebola.ui.UiEvent

@Composable
fun AddJogadorScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = BateBolaDatabaseProvider.provide(context)
    val repository = JogadorRepositoryImpl(dao = database.jogadorDao)

    val viewModel = viewModel<AddJogadorViewModel> {
        AddJogadorViewModel(
            jogadorId = id,
            repository = repository
        )
    }

    val nome = viewModel.nome
    val posicao = viewModel.posicao
    val idade = viewModel.idade
    val capitao = viewModel.capitao

    val snackbarHostState = remember { SnackbarHostState() }


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.NavigateBack -> navigateBack()
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = event.message,)
                }

                is UiEvent.Navigate<*> -> {}
            }
        }
    }

    AddJogadorContent(
        nome = nome,
        posicao = posicao,
        idade = idade,
        capitao = capitao,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun AddJogadorContent(
    nome: String,
    posicao: String,
    idade: Int,
    capitao: Boolean,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddJogadorEvent) -> Unit,
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Botão de Voltar
        IconButton(
            onClick = { onEvent(AddJogadorEvent.OnNavigateBack) },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                tint = Color.Black
            )
        }

        // Título
        Text(
            text = "ADICIONAR JOGADOR",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { onEvent(AddJogadorEvent.OnNomeChange(it)) },
            label = { Text("Nome do jogador") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        // Campo Posição
        OutlinedTextField(
            value = posicao,
            onValueChange = { onEvent(AddJogadorEvent.OnPosicaoChange(it)) },
            label = { Text("Posição do jogador") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        // Campo Idade
        OutlinedTextField(
            value = idade.toString(),
            onValueChange = {
                it.toIntOrNull()?.let { age ->
                    onEvent(AddJogadorEvent.OnIdadeChange(age))
                }
            },
            label = { Text("Idade do jogador") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        // Campo Capitão (Checkbox)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = capitao,
                onCheckedChange = { onEvent(AddJogadorEvent.IsCapitaoChange(it)) }
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(text = "Capitão")
        }
        Spacer(modifier = Modifier.padding(16.dp))

        // Botão Salvar
        Button(
            onClick = { onEvent(AddJogadorEvent.OnSaveJogadorClick) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04330A))
        ) {
            Text("Salvar jogador", fontSize = 18.sp, color = Color.White)
        }
    }
}


