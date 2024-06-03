package com.ricky.controle_pet.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ricky.controle_pet.utils.Constants

@Composable
fun BottomNav(navController: NavHostController, petId: String) {
    NavHost(
        navController = navController,
        startDestination = BottomScreens.VacinasScreen.route + "/${Constants.PARAM_PET_ID}"
    ) {
        composable(BottomScreens.VacinasScreen.route + "/${Constants.PARAM_PET_ID}") {

        }

        composable(BottomScreens.VermifugacaoScreen.route+ "/${Constants.PARAM_PET_ID}") {

        }
    }

}