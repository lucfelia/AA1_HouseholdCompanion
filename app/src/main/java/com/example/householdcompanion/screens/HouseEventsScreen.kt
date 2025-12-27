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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.stringResource

@Composable
fun HouseEventsScreen(
    baseStats: Map<String, Int>,
    onBack: () -> Unit,
    onSave: (Map<String, Int>) -> Unit
) {
    var stats by remember { mutableStateOf(baseStats) }

    val events = listOf(
        stringResource(R.string.calamidad) to -3,
        stringResource(R.string.traici_n) to -2,
        stringResource(R.string.infraestructura) to +2,
        stringResource(R.string.favor) to +1,
        stringResource(R.string.auge) to -1,
        stringResource(R.string.conquista) to +1
    )

    fun applyEvent(modifier: Int) {
        val key = stats.keys.random()
        stats = stats.toMutableMap().also {
            it[key] = (it[key] ?: 0) + modifier
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
                    title = stringResource(R.string.crea_una_casa),
                    left = {
                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black.copy(alpha = 0.60f),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) {Text(stringResource(R.string.atr_s), color = Color.White)}
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
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
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            Text(
                                text = stringResource(R.string._3_sucesos_hist_ricos),
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White
                            )

                            events.forEach { (name, mod) ->
                                Button(
                                    onClick = { applyEvent(mod) },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black.copy(alpha = 0.65f),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        "$name (${if (mod > 0) "+$mod" else mod})",
                                        color = Color.White
                                    )
                                }
                            }

                            Spacer(Modifier.height(8.dp))

                            Text(
                                stringResource(R.string.estado_actual),
                                style = MaterialTheme.typography.titleSmall,
                                color = Color.White
                            )

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
                                    stats.forEach { (k, v) ->
                                        Text("$k: $v", color = Color.White)
                                    }
                                }
                            }

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    onClick = onBack,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black.copy(alpha = 0.60f),
                                        contentColor = Color.White
                                    )
                                ) {Text(stringResource(R.string.atr_s), color = Color.White)}

                                Button(
                                    onClick = { onSave(stats) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black.copy(alpha = 0.75f),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(stringResource(R.string.guardar), color = Color.White)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
