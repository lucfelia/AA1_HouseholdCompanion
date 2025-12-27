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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.R
import com.example.householdcompanion.data.House
import com.example.householdcompanion.data.FakeRepo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    onSave: (House) -> Unit,
    onCancel: () -> Unit
) {
    var nombre by remember { mutableStateOf(stringResource(R.string.detail_default_name)) }
    var lema by remember { mutableStateOf(stringResource(R.string.detail_default_motto)) }
    var emblema by remember { mutableStateOf(stringResource(R.string.detail_default_emblem)) }
    val spacing = dimensionResource(id = R.dimen.spacing_large)
    val smallSpacing = dimensionResource(id = R.dimen.spacing_small)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing)
    ) {
        item {
            Text(
                text = stringResource(R.string.detail_title),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                    .padding(spacing)
            ) {
                Column {
                    Text(stringResource(R.string.detail_identity_section))
                    Spacer(Modifier.height(smallSpacing))

                    Text(stringResource(R.string.detail_name_label))
                    TextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                    Text(stringResource(R.string.detail_motto_label))
                    TextField(
                        value = lema,
                        onValueChange = { lema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
                    Text(stringResource(R.string.detail_emblem_label))
                    TextField(
                        value = emblema,
                        onValueChange = { emblema = it },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            Spacer(Modifier.height(spacing))
            Text(stringResource(R.string.detail_choose_emblem))
            Spacer(Modifier.height(smallSpacing))
        }

        items(listOf(0, 1)) { _ ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(smallSpacing)
            ) {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(dimensionResource(id = R.dimen.tile_height))
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.detail_emblem_placeholder))
                    }
                }
            }
            Spacer(Modifier.height(smallSpacing))
        }

        item {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
            Text(stringResource(R.string.detail_colors_label))
            Spacer(Modifier.height(smallSpacing))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(smallSpacing)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensionResource(id = R.dimen.color_swatch))
                        .background(Color(0xFFDDE1E6))
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensionResource(id = R.dimen.color_swatch))
                        .background(Color(0xFFB5BAC3))
                )
            }

            Spacer(Modifier.height(spacing))
            Row(Modifier.fillMaxWidth()) {
                Button(
                    onClick = onCancel,
                    modifier = Modifier
                        .weight(1f)
                        .height(dimensionResource(id = R.dimen.button_height))
                ) {
                    Text(stringResource(R.string.action_cancel))
                }

                Spacer(Modifier.width(dimensionResource(id = R.dimen.spacing_medium)))

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
                        .height(dimensionResource(id = R.dimen.button_height))
                ) {
                    Text(stringResource(R.string.action_save_and_back))
                }
            }
        }
    }
}
