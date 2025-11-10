package com.example.householdcompanion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.data.House
import com.example.householdcompanion.data.FakeRepo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onSave: (House) -> Unit,
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
                    onClick = onCancel,
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) { Text("Cancelar") }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = {
                        onSave(
                            House(
                                id = FakeRepo.newId(),
                                nombre = nombre,
                                lema = lema,
                                emblema = emblema
                            )
                        )
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(44.dp)
                ) { Text("Guardar y volver") }
            }
        }
    }
}
