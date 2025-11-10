package com.example.householdcompanion.data

import java.util.UUID

object FakeRepo {
    fun newId(): String = UUID.randomUUID().toString()

    fun seed(): List<House> = listOf(
        House(id = newId(), nombre="House Stark", lema="Winter Is Coming", emblema="Lobo huargo"),
        House(id = newId(), nombre="House Tully", lema="Family, Duty, Honor", emblema="Trucha", compartida = true),
        House(id = newId(), nombre="House Arryn", lema="As High as Honor", emblema="Halcón"),
        House(id = newId(), nombre="House Martell", lema="Unbowed, Unbent, Unbroken", emblema="Sol y lanza", compartida = true),
        House(id = newId(), nombre="House Tyrell", lema="Growing Strong", emblema="Rosa")
    )

    fun getStats(list: List<House>) = Stats(
        actuales = list.count { !it.destruida },
        destruidas = list.count { it.destruida },
        deAmigos = list.count { it.compartida }
    )
}
