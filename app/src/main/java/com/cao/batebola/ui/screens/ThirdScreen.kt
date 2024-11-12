package com.cao.batebola.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cao.batebola.R
import com.cao.batebola.dados.model.Jogador
import com.cao.batebola.ui.screens.utils.TopBar

@Composable
fun ThirdScreen(drawerState: DrawerState, navController: NavHostController) {
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
            Text(
                text = "ID: ${jogador.id}",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun FloatButtonThird(navController: NavHostController) {
    FloatingActionButton(
        onClick = {
            navController.navigate("add_player")
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

