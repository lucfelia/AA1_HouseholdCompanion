package com.example.householdcompanion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLogin: (String) -> Unit
) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(Modifier.height(12.dp))
            Text("Household companion", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
            Text("Gestor de casas para campañas de rol", textAlign = TextAlign.Center)

            Spacer(Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column {
                    Text("Sea usted bienvenido,")
                    Text("ingrese sus datos para acceder al compendio de casas nobles.", color = MaterialTheme.colorScheme.onSurfaceVariant)

                    Spacer(Modifier.height(12.dp))
                    Text("Usuario")
                    TextField(
                        value = user,
                        onValueChange = { user = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))
                    Text("Contraseña")
                    TextField(
                        value = pass,
                        onValueChange = { pass = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    if (error != null) {
                        Spacer(Modifier.height(8.dp))
                        Text(error!!, color = MaterialTheme.colorScheme.error)
                    }

                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            if (user.isBlank()) error = "Usuario requerido"
                            else onLogin(user)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) { Text("Entrar al compendio") }
                }
            }
        }
    }
}
