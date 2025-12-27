package com.example.householdcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.data.House

@Composable
fun HouseViewScreen(
    house: House,
    onBack: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(Modifier.fillMaxSize().padding(16.dp)) {

            TopBar(
                title = house.nombre,
                left = { TextButton(onClick = onBack) { Text("Volver") } }
            )

            Spacer(Modifier.height(16.dp))

            Text(house.lema, style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(8.dp))
            Text("Emblema: ${house.emblema}")
            Spacer(Modifier.height(8.dp))
            Text("Región: ${house.region}")

            Spacer(Modifier.height(16.dp))
            Text("Atributos", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))


            house.stats.forEach { (k, v) ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(k)
                    Text(v.toString())
                }
                Spacer(Modifier.height(6.dp))
            }
        }
    }
}
