package com.example.householdcompanion

import androidx.compose.runtime.*
import com.example.householdcompanion.data.FakeRepo
import com.example.householdcompanion.data.House
import com.example.householdcompanion.screens.DetailScreen
import com.example.householdcompanion.screens.HomeScreen
import com.example.householdcompanion.screens.LoginScreen

@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf("login") }

    var currentUser by remember { mutableStateOf<String?>(null) }
    var houses by remember { mutableStateOf(FakeRepo.seed().toMutableList()) }

    fun addHouse(h: House) {
        houses = (houses + h.copy(id = FakeRepo.newId())).toMutableList()
    }

    when (screen) {
        "login" -> LoginScreen(
            onLogin = { username ->
                currentUser = username
                screen = "home"
            }
        )

        "home" -> HomeScreen(
            username = currentUser ?: "Invitado",
            stats = FakeRepo.getStats(houses),
            onCreateHouse = { screen = "detail" },
            onLogout = {
                currentUser = null
                screen = "login"
            }
        )

        "detail" -> DetailScreen(
            onSave = { house ->
                addHouse(house)
                screen = "home"
            },
            onCancel = { screen = "home" }
        )
    }
}
