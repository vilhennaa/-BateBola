package com.cao.batebola.ui.feature.seutime

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.cao.batebola.dados.entity.JogadorEntity
import com.cao.batebola.ui.screens.utils.TopBar
import com.cao.batebola.R
import com.cao.batebola.dados.BateBolaDatabaseProvider
import com.cao.batebola.dados.repository.JogadorRepositoryImpl
import com.cao.batebola.domain.Jogador
import com.cao.batebola.ui.UiEvent
import com.cao.batebola.ui.components.JogadorCard

@Composable
fun ThirdScreen(drawerState: DrawerState, navController: NavHostController) {
    // Dados fictícios para jogadores
    val jogadores = listOf(
        JogadorEntity(nome = "Carlos Silva", idade = 25, posicao = "Atacante"),
        JogadorEntity(nome = "Roberto Souza", idade = 22, posicao = "goleiro"),
        JogadorEntity(nome = "Thiago Lima", idade = 28, posicao = "lateral")
    )

    Scaffold(
        topBar = { TopBar(drawerState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                TeamHeader(
                    teamName = "BarSemLona FC",
                    imageRes = R.drawable.img
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    contentPadding = PaddingValues(16.dp)
                ) {
//                    items(jogadores) { jogador ->
//                        JogadorCard(jogador = jogador)
//                    }
                }
            }
        },
        floatingActionButton = { FloatButtonThird(navController) }
    )
}

@Composable
fun TeamHeader(teamName: String, imageRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Logo do Time",
                modifier = Modifier
                    .size(110.dp)
                    .padding(end = 16.dp)
            )
            Text(
                text = teamName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,

            )
        }
    }
}

@Composable
fun JogadorCard(jogador: Jogador) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nome: ${jogador.nome}",
                fontSize = 24.sp,
                color = Color.Black
            )
            Text(
                text = "Posição: ${jogador.idade}",
                fontSize = 16.sp,
                color = Color.DarkGray
            )
        }
    }
}

@Composable
fun FloatButtonThird(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("add_jogador")
        },
        containerColor = Color(0xFF064D0C)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Adicionar jogador",
            tint = Color.White
        )
    }
}

@Composable
fun SeuTimeScreen(
    navigateToAddJogadorScreen: (Long?) -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = BateBolaDatabaseProvider.provide(context)
    val repository = JogadorRepositoryImpl(dao = database.jogadorDao)

    val viewModel = viewModel<SeuTimeViewModel> { SeuTimeViewModel(repository = repository) }

    val jogadores by viewModel.jogadores.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when (uiEvent.route) {
//                        is AddJogadorRoute -> {
//                            navigateToAddJogadorScreen(uiEvent.route.id)
//                        }
                    }
                }
                UiEvent.NavigateBack -> {}
                is UiEvent.ShowSnackbar -> {}
            }
        }
    }

    SeuTimeContent(
        jogadores = jogadores,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SeuTimeContent(
    jogadores: List<Jogador>,
    onEvent: (SeuTimeEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(SeuTimeEvent.AddJogador(null))
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier.consumeWindowInsets(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(jogadores) { index, jogador ->
                JogadorCard(
                    jogadores = jogador,
                    onItemClick = { onEvent(SeuTimeEvent.AddJogador(jogador.id)) },
                    onDeleteClick = { onEvent(SeuTimeEvent.DeleteJogador(jogador.id)) },
                )

                if (index < jogadores.lastIndex) {
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        }
    }
}
