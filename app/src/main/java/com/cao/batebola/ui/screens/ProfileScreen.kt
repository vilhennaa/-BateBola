package com.cao.batebola.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cao.batebola.R
import com.cao.batebola.ui.screens.utils.TopBar

@Composable
fun ProfileScreen(drawerState: DrawerState) {
    Scaffold(
        topBar = {
             TopBar(drawerState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                shape = CircleShape,
                color = Color.Gray
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Foto do perfil",
                    modifier = Modifier.fillMaxSize()
                )
            }


            Text(
                text = "Nome do Usuário",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )


            Text(
                text = "Uma breve descrição sobre o usuário.",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(vertical = 8.dp)
            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(
                    onClick = { /* Ação de Sair */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04330A)) // Verde hexadecimal
                ) {
                    Text("Editar perfil")
                }
                Button(
                    onClick = { /* Ação de Sair */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF04330A)) // Verde hexadecimal
                ) {
                    Text("Sair")
                }
            }
        }
    }
}
