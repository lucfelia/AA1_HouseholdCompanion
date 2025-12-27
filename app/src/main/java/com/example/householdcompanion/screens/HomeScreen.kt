package com.example.householdcompanion.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.R
import com.example.householdcompanion.data.Stats
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(
    username: String,
    stats: Stats,
    onCreateHouse: () -> Unit,
    onViewHouses: () -> Unit,
    onSharedHouses: () -> Unit,
    onLogout: () -> Unit
) {
    val spaceL = dimensionResource(R.dimen.space_l)
    val spaceM = dimensionResource(R.dimen.space_m)
    val spaceS = dimensionResource(R.dimen.space_s)

    var showStats by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize()) {


        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {
            Column(Modifier.fillMaxSize()) {
                TopBar(
                    title = if (username.isBlank()) stringResource(R.string.home_title) else username,
                    left = { Text("Castillos") },
                    right = {
                        TextButton(onClick = onLogout) { Text(stringResource(R.string.logout)) }
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = spaceL),
                    verticalArrangement = Arrangement.spacedBy(spaceM)
                ) {
                    Spacer(Modifier.height(spaceS))

                    ActionTile(
                        title = stringResource(R.string.home_action_create_house),
                        onClick = onCreateHouse
                    )
                    ActionTile(
                        title = stringResource(R.string.home_action_view_houses),
                        onClick = onViewHouses
                    )
                    ActionTile(
                        title = stringResource(R.string.home_action_shared_houses),
                        onClick = onSharedHouses
                    )

                    SectionCard(
                        titleRes = R.string.home_stats_title,
                        modifier = Modifier.animateContentSize()
                    ) {
                        TextButton(onClick = { showStats = !showStats }) {
                            Text(if (showStats) stringResource(R.string.hide) else stringResource(R.string.show))
                        }

                        AnimatedVisibility(visible = showStats) {
                            Column(verticalArrangement = Arrangement.spacedBy(spaceS)) {
                                StatLine(
                                    label = stringResource(R.string.stats_current),
                                    value = stringResource(R.string.home_stats_current, stats.actuales)
                                )

                                StatLine(
                                    label = stringResource(R.string.stats_destroyed),
                                    value = stringResource(R.string.home_stats_destroyed, stats.destruidas)
                                )

                                StatLine(
                                    label = stringResource(R.string.stats_friends),
                                    value = stringResource(R.string.home_stats_friends, stats.deAmigos)
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(spaceM))
                }
            }
        }
    }
}

@Composable
private fun StatLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyLarge)
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}
