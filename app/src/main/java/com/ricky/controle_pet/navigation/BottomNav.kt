package com.ricky.controle_pet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNav(navController: NavHostController, petId: String) {
    NavHost(navController = navController, startDestination = BottomScreens.VacinasScreen.route) {
        composable(BottomScreens.VacinasScreen.route){

        }

        composable(BottomScreens.VermifugacaoScreen.route){

        }
    }

}