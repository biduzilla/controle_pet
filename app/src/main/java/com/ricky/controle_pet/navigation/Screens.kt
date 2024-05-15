package com.ricky.controle_pet.navigation

sealed class Screens(val route: String) {
    data object SplashScreen : Screens(route = "splash_screen")
    data object HomeScreen : Screens(route = "home_screen")
    data object FormScreen : Screens(route = "form_screen")
    data object PetsScreen : Screens(route = "pets_screen")
}