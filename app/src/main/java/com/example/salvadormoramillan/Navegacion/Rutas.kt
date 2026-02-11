package com.example.salvadormoramillan.Navegacion

import kotlinx.serialization.Serializable

@Serializable
sealed class Rutas(){

    @Serializable
    data object Login : Rutas()

    @Serializable
    data object Home : Rutas()


}