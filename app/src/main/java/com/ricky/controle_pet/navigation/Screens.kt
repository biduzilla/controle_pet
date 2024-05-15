package com.ricky.controle_pet.navigation

sealed class Screens(val route: String) {
    data object SplashScreen : Screens(route = "splash")
    data object HomeScreen : Screens(route = "home")
    data object FormScreen : Screens(route = "form")
}