package com.example.householdcompanion.data

import java.util.UUID

object FakeRepo {

    fun newId(): String = UUID.randomUUID().toString()

    // Stats base fake (para demo)
    private fun baseStats() = mapOf(
        "Tierras" to 18,
        "Defensa" to 18,
        "Influencia" to 18,
        "Prestigio" to 18,
        "Milicia" to 18,
        "Economía" to 18,
        "Lealtad" to 18
    )

    fun seed(): List<House> = listOf(
        House(
            id = newId(),
            nombre = "House Stark",
            lema = "Winter Is Coming",
            emblema = "Lobo huargo",
            region = "Norte",
            stats = baseStats()
        ),
        House(
            id = newId(),
            nombre = "House Tully",
            lema = "Family, Duty, Honor",
            emblema = "Trucha",
            region = "Tierras de los ríos",
            stats = baseStats(),
            compartida = true
        ),
        House(
            id = newId(),
            nombre = "House Arryn",
            lema = "As High as Honor",
            emblema = "Halcón",
            region = "Valle",
            stats = baseStats()
        ),
        House(
            id = newId(),
            nombre = "House Martell",
            lema = "Unbowed, Unbent, Unbroken",
            emblema = "Sol y lanza",
            region = "Dorne",
            stats = baseStats(),
            compartida = true
        ),
        House(
            id = newId(),
            nombre = "House Tyrell",
            lema = "Growing Strong",
            emblema = "Rosa",
            region = "Dominio",
            stats = baseStats()
        )
    )

    fun getStats(list: List<House>) = Stats(
        actuales = list.count { !it.destruida },
        destruidas = list.count { it.destruida },
        deAmigos = list.count { it.compartida }
    )
}
