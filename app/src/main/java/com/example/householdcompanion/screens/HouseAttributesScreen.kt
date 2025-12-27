package com.example.householdcompanion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

        Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {
            Column(Modifier.fillMaxSize()) {

                TopBar(
                    title = "Crea una casa",
                    left = {
                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black.copy(alpha = 0.60f),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) { Text("Atrás") }
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(alpha = 0.55f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text("2. Atributos iniciales", style = MaterialTheme.typography.titleMedium, color = Color.White)

                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded }
                            ) {
                                TextField(
                                    value = selectedRegion,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text("Región") },
                                    modifier = Modifier
                                        .menuAnchor()
                                        .fillMaxWidth()
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

                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.Black.copy(alpha = 0.35f)
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(12.dp),
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    statNames.forEach {
                                        Text(
                                            text = "$it: ${stats[it] ?: "-"}",
                                            color = Color.White
                                        )
                                    }
                                }
                            }

                            Button(
                                onClick = { rollStats() },
                                modifier = Modifier.align(Alignment.End),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Black.copy(alpha = 0.65f),
                                    contentColor = Color.White
                                )
                            ) {
                                Text("Roll 🎲")
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = onBack,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black.copy(alpha = 0.60f),
                                        contentColor = Color.White
                                    )
                                ) { Text("Atrás") }

                                Button(
                                    enabled = stats.isNotEmpty(),
                                    onClick = { onNext(selectedRegion, stats) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black.copy(alpha = 0.75f),
                                        contentColor = Color.White,
                                        disabledContainerColor = Color.Black.copy(alpha = 0.35f),
                                        disabledContentColor = Color.White.copy(alpha = 0.50f)
                                    )
                                ) { Text("Siguiente") }
                            }
                        }
                    }
                }
            }
        }
    }
}
