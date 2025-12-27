package com.example.householdcompanion

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.householdcompanion.data.FakeRepo
import com.example.householdcompanion.data.House
import com.example.householdcompanion.data.UserProfile
import com.example.householdcompanion.screens.DetailScreen
import com.example.householdcompanion.screens.HomeScreen
import com.example.householdcompanion.screens.LoginScreen
import com.example.householdcompanion.R

@Composable
fun AppRoot() {
    var screen by remember { mutableStateOf("login") }

    var currentUser by remember { mutableStateOf<String?>(null) }
    var houses by remember { mutableStateOf(FakeRepo.seed().toMutableList()) }
    var userProfile by remember { mutableStateOf<UserProfile?>(null) }

    fun handleLogin(username: String) {
        currentUser = username
        userProfile = FakeRepo.loadUser(username)
        screen = "home"
    }

    fun addHouse(h: House) {
        houses = (houses + h.copy(id = FakeRepo.newId())).toMutableList()
    }

    Crossfade(targetState = screen) { target ->
        when (target) {
            "login" -> LoginScreen(
                onLogin = { username -> handleLogin(username) }
            )

            "home" -> HomeScreen(
                username = currentUser ?: stringResource(R.string.home_guest_user),
                stats = FakeRepo.getStats(houses),
                userProfile = userProfile,
                onCreateHouse = { screen = "detail" },
                onLogout = {
                    currentUser = null
                    userProfile = null
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
}
