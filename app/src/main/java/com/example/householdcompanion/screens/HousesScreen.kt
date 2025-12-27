package com.example.householdcompanion.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.R
import com.example.householdcompanion.data.House

@Composable
fun HousesScreen(
    houses: List<House>,
    onBack: () -> Unit,
    onOpenHouse: (House) -> Unit
) {
    val spaceL = dimensionResource(R.dimen.space_l)
    val spaceM = dimensionResource(R.dimen.space_m)
    val spaceS = dimensionResource(R.dimen.space_s)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(Modifier.fillMaxSize()) {

            TopBar(
                title = stringResource(R.string.home_action_view_houses),
                left = { TextButton(onClick = onBack) { Text(stringResource(R.string.back)) } }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spaceL),
                verticalArrangement = Arrangement.spacedBy(spaceM)
            ) {
                Spacer(Modifier.height(spaceS))

                if (houses.isEmpty()) {
                    Text("No hay casas todavía.")
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(spaceS)) {
                        items(houses, key = { it.id }) { h ->
                            HouseItem(
                                h = h,
                                onClick = { onOpenHouse(h) }
                            )
                        }
                    }
                }

                Spacer(Modifier.height(spaceM))
            }
        }
    }
}

@Composable
private fun HouseItem(
    h: House,
    onClick: () -> Unit
) {
    val spaceM = dimensionResource(R.dimen.space_m)
    val spaceS = dimensionResource(R.dimen.space_s)

    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(Modifier.fillMaxWidth().padding(spaceM)) {
            Text(h.nombre, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(spaceS))
            Text(h.lema, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
