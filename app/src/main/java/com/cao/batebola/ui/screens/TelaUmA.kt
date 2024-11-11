package com.cao.batebola.ui.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaUmA(padding: PaddingValues) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val usernameState = remember { mutableStateOf("") }
        val passwordState = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Login",
            Modifier.padding(padding),
            fontSize = 40.sp
        )
        Text(
            text = "Nome de usuário:",
            fontSize = 20.sp
        )
        TextField(
            value = usernameState.value,
            onValueChange = { usernameState.value = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Senha:",
            fontSize = 20.sp
        )
        TextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(50.dp))
        TextButton(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF04330A)
            ),
            onClick = {
                // Ação do botão de login
            }) {

            Text(
                text = "ENTRAR", fontSize = 30.sp,
                color = Color.White
            )
        }
    }
}