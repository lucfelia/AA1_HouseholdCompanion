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
    Login, Home, Detail, Attributes, Events, Houses, Shared, HouseView
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf(Screen.Login) }

    var currentUser by remember { mutableStateOf<String?>(null) }
    var houses by remember { mutableStateOf(FakeRepo.seed().toMutableList()) }


    var tempHouse by remember { mutableStateOf<House?>(null) }
    var tempStats by remember { mutableStateOf<Map<String, Int>>(emptyMap()) }
    var tempRegion by remember { mutableStateOf("") }


    var selectedHouseId by remember { mutableStateOf<String?>(null) }

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
                onNext = { region, stats ->
                    tempRegion = region
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
                                region = tempRegion,
                                stats = finalStats
                            )
                        )
                    }
                    tempHouse = null
                    tempStats = emptyMap()
                    tempRegion = ""
                    screen = Screen.Home
                }
            )


            Screen.Houses -> HousesScreen(
                houses = houses,
                onBack = { screen = Screen.Home },
                onOpenHouse = { h ->
                    selectedHouseId = h.id
                    screen = Screen.HouseView
                }
            )

            Screen.Shared -> SharedHousesScreen(
                houses = houses,
                onBack = { screen = Screen.Home },
                onOpenHouse = { h ->
                    selectedHouseId = h.id
                    screen = Screen.HouseView
                }
            )


            Screen.HouseView -> {
                val house = houses.firstOrNull { it.id == selectedHouseId }
                if (house == null) {
                    screen = Screen.Home
                } else {
                    HouseViewScreen(
                        house = house,
                        onBack = { screen = Screen.Home }
                    )
                }
            }
        }
    }
}
