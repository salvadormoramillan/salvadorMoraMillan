package com.example.salvadormoramillan.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.salvadormoramillan.Screen.HomeScreen
import com.example.salvadormoramillan.Screen.LoginScreen

@Composable
fun Navegacion(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.Home
    ) {
        composable<Rutas.Login> {
            LoginScreen(
                OnLoginSuccess = {
                    navController.navigate(Rutas.Home){
                        popUpTo(Rutas.Login){inclusive = true}
                    }
                }
            )
        }

        composable<Rutas.Home> {
            HomeScreen()
        }
    }
}