package com.example.householdcompanion

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.*
import com.example.householdcompanion.data.FakeRepo
import com.example.householdcompanion.data.House
import com.example.householdcompanion.screens.*

private enum class Screen {
    Login, Home, Detail,
    Attributes, Events,
    Houses, Shared
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf(Screen.Login) }

    var currentUser by remember { mutableStateOf<String?>(null) }
    var houses by remember { mutableStateOf(FakeRepo.seed().toMutableList()) }

    // 🔹 ESTADO TEMPORAL PARA CREACIÓN DE CASA
    var tempHouse by remember { mutableStateOf<House?>(null) }
    var tempStats by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }

    fun addHouse(h: House) {
        houses = (houses + h).toMutableList()
    }

    AnimatedContent(
        targetState = screen,
        transitionSpec = { fadeIn() with fadeOut() }
    ) { target ->
        when (target) {

            Screen.Login -> LoginScreen(
                onLogin = { username ->
                    currentUser = username
                    screen = Screen.Home
                }
            )

            Screen.Home -> HomeScreen(
                username = currentUser ?: "Invitado",
                stats = FakeRepo.getStats(houses),
                onCreateHouse = { screen = Screen.Detail },
                onViewHouses = { screen = Screen.Houses },
                onSharedHouses = { screen = Screen.Shared },
                onLogout = {
                    currentUser = null
                    screen = Screen.Login
                }
            )


            Screen.Detail -> DetailScreen(
                onSave = { house ->
                    tempHouse = house
                    screen = Screen.Attributes
                },
                onCancel = { screen = Screen.Home }
            )


            Screen.Attributes -> HouseAttributesScreen(
                onBack = { screen = Screen.Detail },
                onNext = { _, stats ->
                    tempStats = stats
                    screen = Screen.Events
                }
            )


            Screen.Events -> HouseEventsScreen(
                baseStats = tempStats,
                onBack = { screen = Screen.Attributes },
                onSave = { finalStats ->
                    val base = tempHouse
                    if (base != null) {
                        addHouse(
                            base.copy(
                                stats = finalStats
                            )
                        )
                    }
                    tempHouse = null
                    tempStats = emptyMap()
                    screen = Screen.Home
                }
            )

            Screen.Houses -> HousesScreen(
                houses = houses,
                onBack = { screen = Screen.Home }
            )

            Screen.Shared -> SharedHousesScreen(
                houses = houses,
                onBack = { screen = Screen.Home }
            )
        }
    }
}
