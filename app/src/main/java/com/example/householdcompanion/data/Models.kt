package com.example.householdcompanion.data

data class House(
    val id: String,
    val nombre: String,
    val lema: String,
    val emblema: String,
    val colorPrimario: Long? = 0xFFDDE1E6,
    val colorSecundario: Long? = 0xFFB5BAC3,
    val destruida: Boolean = false,
    val compartida: Boolean = false
)

data class Stats(
    val actuales: Int,
    val destruidas: Int,
    val deAmigos: Int
)

data class UserProfile(
    val username: String,
    val titulo: String,
    val nivel: Int
)
