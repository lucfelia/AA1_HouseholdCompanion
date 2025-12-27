package com.example.householdcompanion.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.householdcompanion.R
import com.example.householdcompanion.data.Stats
import com.example.householdcompanion.data.UserProfile

@Composable
fun HomeScreen(
    username: String,
    stats: Stats,
    userProfile: UserProfile?,
    onCreateHouse: () -> Unit,
    onLogout: () -> Unit
) {
    var showStats by remember { mutableStateOf(true) }
    val spacing = dimensionResource(id = R.dimen.spacing_large)
    val cardPadding = dimensionResource(id = R.dimen.spacing_medium)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = spacing, vertical = spacing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.avatar_size))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.home_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.avatar_size))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                )
            }
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

            Text(
                text = stringResource(R.string.home_welcome, username),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(spacing))
            Text(
                text = stringResource(R.string.home_actions_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
        }

        val acciones = listOf(
            stringResource(R.string.home_action_create) to onCreateHouse,
            stringResource(R.string.home_action_view) to {},
            stringResource(R.string.home_action_shared) to {}
        )

        items(acciones) { (titulo, onClick) ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                    .animateContentSize()
                    .padding(cardPadding)
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = titulo,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
                    Button(
                        onClick = onClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.button_height))
                    ) { Text(titulo) }
                }
            }
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_medium)))
        }

        item {
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
            Text(
                text = stringResource(R.string.home_stats_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))

            Button(
                onClick = { showStats = !showStats },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (showStats) stringResource(R.string.home_stats_toggle_hide)
                    else stringResource(R.string.home_stats_toggle_show)
                )
            }

            AnimatedVisibility(visible = showStats) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium)
                        .padding(cardPadding)
                ) {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if (userProfile != null) {
                            Text(
                                stringResource(
                                    R.string.home_profile_header,
                                    userProfile.titulo,
                                    userProfile.nivel
                                ),
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(Modifier.height(dimensionResource(id = R.dimen.spacing_small)))
                        }
                        Text(
                            stringResource(R.string.home_stats_current, stats.actuales),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            stringResource(R.string.home_stats_destroyed, stats.destruidas),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            stringResource(R.string.home_stats_friends, stats.deAmigos),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }

            Spacer(Modifier.height(spacing))
            Button(
                onClick = onLogout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.button_height))
            ) { Text(stringResource(R.string.home_action_logout)) }
        }
    }
}
