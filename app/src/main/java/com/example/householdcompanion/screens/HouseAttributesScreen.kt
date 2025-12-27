package com.example.householdcompanion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HouseAttributesScreen(
    onNext: (String, Map<String, Int>) -> Unit,
    onBack: () -> Unit
) {
    val regions = listOf("Tierras de los ríos", "Norte", "Occidente", "Dominio", "Dorne")
    var selectedRegion by remember { mutableStateOf(regions.first()) }
    var expanded by remember { mutableStateOf(false) }

    val statNames = listOf(
        "Tierras", "Defensa", "Influencia",
        "Prestigio", "Milicia", "Economía", "Lealtad"
    )

    var stats by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }

    fun rollStats() {
        stats = statNames.associateWith {
            (1..2).sumOf { (1..6).random() } + 6
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Surface(modifier = Modifier.fillMaxSize(), color = androidx.compose.ui.graphics.Color.Transparent) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("2. Atributos iniciales", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(16.dp))


                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = selectedRegion,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Región") },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        regions.forEach { region ->
                            DropdownMenuItem(
                                text = { Text(region) },
                                onClick = {
                                    selectedRegion = region
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                statNames.forEach {
                    Text("$it: ${stats[it] ?: "-"}")
                }

                Spacer(Modifier.height(16.dp))

                Button(onClick = { rollStats() }) {
                    Text("Roll 🎲")
                }

                Spacer(Modifier.height(16.dp))

                Row {
                    Button(onClick = onBack) {
                        Text("Atrás")
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(
                        enabled = stats.isNotEmpty(),
                        onClick = { onNext(selectedRegion, stats) }
                    ) {
                        Text("Siguiente")
                    }
                }
            }
        }
    }
}
