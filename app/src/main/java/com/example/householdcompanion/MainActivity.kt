package com.example.householdcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.ui.theme.HouseholdCompanionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HouseholdCompanionTheme {
                Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppRoot()
                }
            }
        }
    }
}

@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf("login") }

    when (screen) {
        "login" -> LoginScreen(onLogin = { screen = "home" })
        "home"  -> HomeScreen(
            onLogout = { screen = "login" },
            onCreateHouse = { screen = "detail" }
        )
        "detail" -> DetailScreen(
            onSave = { screen = "home" },
            onCancel = { screen = "home" }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLogin: () -> Unit) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

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
            Text(text = "Household companion", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center)
            Text(text = "Gestor de casas para campañas de rol", textAlign = TextAlign.Center)

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

                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = { onLogin() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) { Text("Entrar al compendio") }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onCreateHouse: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Household companion",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Bienvenido, LordEddardStark",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))
            Text(text = "Acciones", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))
        }

        val acciones = listOf(
            "Crea una casa" to onCreateHouse,
            "Consulta tus casas" to {},
            "Casas compartidas" to {}
        )
        items(acciones) { (titulo, onClick) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(12.dp)
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = titulo, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) { Text(titulo) }
                }
            }
            Spacer(Modifier.height(10.dp))
        }

        item {
            Spacer(Modifier.height(8.dp))
            Text(text = "Stats actuales", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("• Casas actuales: 5", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text("• Casas destruidas: 0", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    Text("• Casas de amigos: 2", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { onLogout() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
            ) { Text("Volver a la Log In") }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    var nombre by remember { mutableStateOf("House Stark") }
    var lema by remember { mutableStateOf("Winter Is Coming") }
    var emblema by remember { mutableStateOf("Lobo huargo") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(text = "Crea una casa", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column {
                    Text("1) Identidad de la casa")
                    Spacer(Modifier.height(8.dp))

                    Text("Nombre")
                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))
                    Text("Lema")
                    TextField(
                        value = lema,
                        onValueChange = { lema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))
                    Text("Emblema")
                    TextField(
                        value = emblema,
                        onValueChange = { emblema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Text("Escoge un emblema")
            Spacer(Modifier.height(8.dp))
        }

        items(listOf(0,1)) { _ ->
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("EMBLEM.")
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        item {
            Spacer(Modifier.height(12.dp))
            Text("Colores")
            Spacer(Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .background(Color(0xFFDDE1E6))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .background(Color(0xFFB5BAC3))
                )
            }

            Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = { onCancel() },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) { Text("Cancelar") }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = { onSave() },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) { Text("Guardar y volver") }
            }
        }
    }
}

@Preview
@Composable
fun AppSinglePreview() {
    HouseholdCompanionTheme {
        AppRoot()
    }
}