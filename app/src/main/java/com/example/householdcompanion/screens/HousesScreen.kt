package com.example.householdcompanion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
                    title = stringResource(R.string.home_action_view_houses),
                    left = {
                        Button(
                            onClick = onBack,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black.copy(alpha = 0.60f),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp)
                        ) { Text(stringResource(R.string.back)) }
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = spaceL),
                    verticalArrangement = Arrangement.spacedBy(spaceM)
                ) {
                    Spacer(Modifier.height(spaceS))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Black.copy(alpha = 0.55f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(spaceM),
                            verticalArrangement = Arrangement.spacedBy(spaceS)
                        ) {
                            if (houses.isEmpty()) {
                                Text("No hay casas todavía.", color = Color.White)
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
                        }
                    }

                    Spacer(Modifier.height(spaceM))
                }
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
