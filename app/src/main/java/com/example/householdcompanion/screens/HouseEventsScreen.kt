package com.example.householdcompanion.screens

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HouseEventsScreen(
    baseStats: Map<String, Int>,
    onBack: () -> Unit,
    onSave: (Map<String, Int>) -> Unit
) {
    var stats by remember { mutableStateOf(baseStats) }

    val events = listOf(
        "Calamidad" to -3,
        "Traición" to -2,
        "Infraestructura" to +2,
        "Favor" to +1,
        "Auge" to -1,
        "Conquista" to +1
    )

    fun applyEvent(modifier: Int) {
        val key = stats.keys.random()
        stats = stats.toMutableMap().also {
            it[key] = (it[key] ?: 0) + modifier
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "3. Sucesos históricos",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(16.dp))

        events.forEach { (name, mod) ->
            Button(
                onClick = { applyEvent(mod) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("$name (${if (mod > 0) "+$mod" else mod})")
            }
            Spacer(Modifier.height(8.dp))
        }

        Spacer(Modifier.height(16.dp))

        Text("Estado actual:", style = MaterialTheme.typography.titleSmall)
        Spacer(Modifier.height(8.dp))

        stats.forEach { (k, v) ->
            Text("$k: $v")
        }

        Spacer(Modifier.height(24.dp))

        Row {
            Button(onClick = onBack) {
                Text("Atrás")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { onSave(stats) }) {
                Text("Guardar")
            }
        }
    }
}


