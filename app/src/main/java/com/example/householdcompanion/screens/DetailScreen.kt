package com.example.householdcompanion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.R
import com.example.householdcompanion.data.FakeRepo
import com.example.householdcompanion.data.House
import com.example.householdcompanion.screens.PrimaryButton
import com.example.householdcompanion.screens.SectionCard
import com.example.householdcompanion.screens.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onSave: (House) -> Unit,
    onCancel: () -> Unit
) {
    val spaceL = dimensionResource(R.dimen.space_l)
    val spaceM = dimensionResource(R.dimen.space_m)
    val spaceS = dimensionResource(R.dimen.space_s)
    val defaultNombre = stringResource(R.string.detail_default_house)
    val defaultLema = stringResource(R.string.detail_default_motto)
    var name by remember { mutableStateOf("") }
    var motto by remember { mutableStateOf("") }
    var emblem by remember { mutableStateOf("I") }
    var color1 by remember { mutableStateOf("") }
    var color2 by remember { mutableStateOf("") }

    val emblems = remember { listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII") }

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(Modifier.fillMaxSize()) {
            TopBar(
                title = stringResource(R.string.detail_title),
                left = { TextButton(onClick = onCancel) { Text(stringResource(R.string.back)) } },
                right = { /* icon placeholder */ }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spaceL),
                verticalArrangement = Arrangement.spacedBy(spaceM)
            ) {
                SectionCard(titleRes = R.string.detail_identity) {
                    Text(text = stringResource(R.string.detail_identity_desc), style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(spaceM))

                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(stringResource(R.string.detail_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(Modifier.height(spaceS))

                    OutlinedTextField(
                        value = motto,
                        onValueChange = { motto = it },
                        label = { Text(stringResource(R.string.detail_motto)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(spaceM))
                    Text(text = stringResource(R.string.detail_emblem), style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(spaceS))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        verticalArrangement = Arrangement.spacedBy(spaceS),
                        horizontalArrangement = Arrangement.spacedBy(spaceS),
                        modifier = Modifier.height(dimensionResource(R.dimen.emblem_grid_h))
                    ) {
                        items(emblems) { e ->
                            FilterChip(
                                selected = emblem == e,
                                onClick = { emblem = e },
                                label = { Text(e) }
                            )
                        }
                    }

                    Spacer(Modifier.height(spaceM))
                    Text(text = stringResource(R.string.detail_colors), style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(spaceS))

                    Row(horizontalArrangement = Arrangement.spacedBy(spaceS)) {
                        OutlinedTextField(
                            value = color1,
                            onValueChange = { color1 = it },
                            label = { Text(stringResource(R.string.detail_color1)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = color2,
                            onValueChange = { color2 = it },
                            label = { Text(stringResource(R.string.detail_color2)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    Spacer(Modifier.height(spaceM))
                    Button(
                        onClick = {
                            onSave(
                                House(
                                    id = FakeRepo.newId(),
                                    nombre = name.ifBlank { defaultNombre },
                                    lema = motto.ifBlank { defaultLema },
                                    emblema = emblem,
                                    region = "",
                                    stats = emptyMap()
                                )
                            )
                        }
                    ) {
                        Text(stringResource(R.string.action_save_and_back))
                    }


                    Spacer(Modifier.height(spaceM))
            }
        }
    }
}
}