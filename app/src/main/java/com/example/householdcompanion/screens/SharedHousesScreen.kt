package com.example.householdcompanion.screens

import androidx.compose.runtime.Composable
import com.example.householdcompanion.data.House
import androidx.compose.ui.graphics.Color

@Composable
fun SharedHousesScreen(
    houses: List<House>,
    onBack: () -> Unit,
    onOpenHouse: (House) -> Unit
) {
    HousesScreen(
        houses = houses.filter { it.compartida },
        onBack = onBack,
        onOpenHouse = onOpenHouse
    )
}
