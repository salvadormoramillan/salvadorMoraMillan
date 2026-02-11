package com.example.salvadormoramillan.State

import com.example.salvadormoramillan.Data.BaseDeDatos

data class HomeState(
    val jugadores: List<BaseDeDatos> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: String? = null,
    val selectedPlayer: BaseDeDatos? = null
)
