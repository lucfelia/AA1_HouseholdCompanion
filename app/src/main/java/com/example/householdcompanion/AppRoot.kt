package com.example.householdcompanion

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.*
import com.example.householdcompanion.data.FakeRepo
import com.example.householdcompanion.data.House
import com.example.householdcompanion.data.Stats
import com.example.householdcompanion.screens.DetailScreen
import com.example.householdcompanion.screens.HomeScreen
import com.example.householdcompanion.screens.LoginScreen

private enum class Screen { Login, Home, Detail }

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf(Screen.Login) }

    var currentUser by remember { mutableStateOf<String?>(null) }
    var houses by remember { mutableStateOf(FakeRepo.seed().toMutableList()) }

    fun addHouse(h: House) {
        houses = (houses + h.copy(id = FakeRepo.newId())).toMutableList()
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
                onViewHouses = {  },
                onSharedHouses = {  },
                onLogout = {
                    currentUser = null
                    screen = Screen.Login
                }
            )

            Screen.Detail -> DetailScreen(
                onSave = { house ->
                    addHouse(house)
                    screen = Screen.Home
                },
                onCancel = { screen = Screen.Home }
            )
        }
    }
}

