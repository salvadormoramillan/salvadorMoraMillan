package com.example.salvadormoramillan.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.salvadormoramillan.Screen.HomeScreen
<<<<<<< HEAD
import com.example.salvadormoramillan.Screen.HomeScreen2
=======
>>>>>>> 7726b1033a19f13def4bba227604ccbbfbe94f22
import com.example.salvadormoramillan.Screen.LoginScreen

@Composable
fun Navegacion(){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
<<<<<<< HEAD
        startDestination = Rutas.Login
=======
        startDestination = Rutas.Home
>>>>>>> 7726b1033a19f13def4bba227604ccbbfbe94f22
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
<<<<<<< HEAD
            HomeScreen2()
=======
            HomeScreen()
>>>>>>> 7726b1033a19f13def4bba227604ccbbfbe94f22
        }
    }
}