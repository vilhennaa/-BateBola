package com.cao.batebola.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CreateTeamScreen(navController: NavController) {
    val teamName = remember { mutableStateOf("") }
    val teamDescription = remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Criar Novo Time",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = teamName.value,
            onValueChange = { teamName.value = it },
            label = { Text("Nome do Time") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(
            value = teamDescription.value,
            onValueChange = { teamDescription.value = it },
            label = { Text("Descrição do Time") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                // Aqui você pode salvar o time ou realizar qualquer outra ação
                // Navegar de volta
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Time", fontSize = 18.sp)
        }
    }
}

