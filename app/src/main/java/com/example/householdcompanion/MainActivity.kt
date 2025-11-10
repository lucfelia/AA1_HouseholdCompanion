package com.example.householdcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(24.dp))

        Text(text = "Household companion", style = MaterialTheme.typography.titleLarge)
        Text(text = "Gestor de casas para campañas de rol")

        Spacer(Modifier.height(24.dp))

        Text(text = "Sea usted bienvenido,")
        Text(text = "ingrese sus datos para acceder al compendio de casas nobles.")

        Spacer(Modifier.height(16.dp))

        Text(text = "Usuario")
        TextField(
            value = user,
            onValueChange = { user = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))

        Text(text = "Contraseña")
        TextField(
            value = pass,
            onValueChange = { pass = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { onLogin() },
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) { Text("Entrar al compendio") }
    }
}

@Composable
fun HomeScreen(
    onLogout: () -> Unit,
    onCreateHouse: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Household companion",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "Bienvenido, LordEddardStark",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Text(text = "Acciones", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { onCreateHouse() },
            modifier = Modifier.fillMaxWidth().height(44.dp)
        ) { Text("Crea una casa") }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(44.dp)
        ) { Text("Consulta tus casas") }

        Spacer(Modifier.height(8.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth().height(44.dp)
        ) { Text("Casas compartidas") }

        Spacer(Modifier.height(24.dp))

        Text(text = "Stats actuales", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text(text = "• Casas actuales: 5", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(text = "• Casas destruidas: 0", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
        Text(text = "• Casas de amigos: 2", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { onLogout() }, // volver a Login
            modifier = Modifier.fillMaxWidth().height(44.dp)
        ) { Text("Volver a la Log In") }
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
    var colorPrimario by remember { mutableStateOf("#DDE1E6") }
    var colorSecundario by remember { mutableStateOf("#B5BAC3") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(text = "Crea una casa", style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(16.dp))
        Text(text = "1) Identidad de la casa")
        Spacer(Modifier.height(8.dp))

        Text(text = "Nombre")
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))
        Text(text = "Lema")
        TextField(
            value = lema,
            onValueChange = { lema = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))
        Text(text = "Emblema (texto)")
        TextField(
            value = emblema,
            onValueChange = { emblema = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(12.dp))
        Text(text = "Colores (texto simple, sin selector)")
        TextField(
            value = colorPrimario,
            onValueChange = { colorPrimario = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))
        TextField(
            value = colorSecundario,
            onValueChange = { colorSecundario = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
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

@Preview
@Composable
fun AppSinglePreview() {
    HouseholdCompanionTheme {
        AppRoot()
    }
}