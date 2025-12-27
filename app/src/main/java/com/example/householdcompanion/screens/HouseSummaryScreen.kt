package com.example.householdcompanion.screens

import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HouseSummaryScreen(
    region: String,
    stats: Map<String, Int>,
    onBack: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Resumen de la casa", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(16.dp))

        Text("Región: $region")
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
            Button(onClick = onConfirm) {
                Text("Confirmar")
            }
        }
    }
}

