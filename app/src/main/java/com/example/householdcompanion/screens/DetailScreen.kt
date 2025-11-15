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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.householdcompanion.R
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
            Text(
                text = stringResource(R.string.detail_title),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(16.dp)
            ) {
                Column {
                    Text(stringResource(R.string.detail_identity_section))
                    Spacer(Modifier.height(8.dp))

                    Text(stringResource(R.string.detail_name_label))
                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))
                    Text(stringResource(R.string.detail_motto_label))
                    TextField(
                        value = lema,
                        onValueChange = { lema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))
                    Text(stringResource(R.string.detail_emblem_label))
                    TextField(
                        value = emblema,
                        onValueChange = { emblema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.detail_choose_emblem))
            Spacer(Modifier.height(8.dp))
        }

        items(listOf(0, 1)) { _ ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.detail_emblem_placeholder))
                    }
                }
            }
            Spacer(Modifier.height(8.dp))
        }

        item {
            Spacer(Modifier.height(12.dp))
            Text(stringResource(R.string.detail_colors_label))
            Spacer(Modifier.height(8.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
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
                ) {
                    Text(stringResource(R.string.action_cancel))
                }

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
                ) {
                    Text(stringResource(R.string.action_save_and_back))
                }
            }
        }
    }
}