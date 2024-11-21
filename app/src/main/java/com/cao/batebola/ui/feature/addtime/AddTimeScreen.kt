package com.cao.batebola.ui.feature.addtime

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cao.batebola.dados.BateBolaDatabaseProvider
import com.cao.batebola.dados.repository.Time.TimeRepositoryImpl
import com.cao.batebola.ui.UiEvent
import com.cao.batebola.ui.screens.utils.TopBar

@Composable
fun AddTimeScreen(
    id: Long?,
    navigateBack: () -> Unit,
    drawerState: DrawerState
) {
    val context = LocalContext.current.applicationContext
    val database = BateBolaDatabaseProvider.provide(context)
    val repository = TimeRepositoryImpl(dao = database.timeDao)

    val viewModel = viewModel<AddTimeViewModel> {
        AddTimeViewModel(
            id = id,
            timeRepository = repository
        )
    }

    val nome = viewModel.nome
    val cidade = viewModel.cidade
    val estado = viewModel.estado

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(message = uiEvent.message)
                }
                UiEvent.NavigateBack -> navigateBack()
                is UiEvent.Navigate<*> -> {}
            }
        }
    }

    AddTimeContent(
        nome = nome,
        cidade = cidade,
        estado = estado,
        snackbarHostState = snackbarHostState,
        onEvent = viewModel::onEvent,
        drawerState = drawerState,
    )
}


@Composable
fun AddTimeContent(
    nome: String,
    cidade: String,
    estado: String,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddTimeEvent) -> Unit,
    drawerState: DrawerState
) {
    Scaffold(
        topBar = { TopBar(drawerState = drawerState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

        ) {
            LazyColumn(
                modifier = Modifier.consumeWindowInsets(paddingValues),
                contentPadding = PaddingValues(16.dp)
            ) {
                // Título
                item {
                    Text(
                        text = "CADASTRAR TIME",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                // Campo Nome
                item {
                    OutlinedTextField(
                        value = nome,
                        onValueChange = { onEvent(AddTimeEvent.NomeChanged(it)) },
                        label = { Text("Nome do Time") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }

                // Campo cidade
                item {
                    OutlinedTextField(
                        value = cidade,
                        onValueChange = { onEvent(AddTimeEvent.CidadeChanged(it)) },
                        label = { Text("Cidade") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }

                // Campo Estado
                item {
                    OutlinedTextField(
                        value = estado,
                        onValueChange = { onEvent(AddTimeEvent.EstadoChanged(it)) },
                        label = { Text("Estado") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                }

                // Botão Salvar
                item {
                    Button(
                        onClick = { onEvent(AddTimeEvent.SaveTime) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04330A))
                    ) {
                        Text("Cadastrar Time", fontSize = 18.sp, color = Color.White)
                    }
                }
            }
        }
    }
}


