package com.ricky.controle_pet.navigation


import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ricky.controle_pet.presentation.vacinas.VacinaViewModel
import com.ricky.controle_pet.presentation.vacinas.VacinasScreen
import com.ricky.controle_pet.utils.Constants

@Composable
fun BottomNav(navController: NavHostController, petId: String) {

    NavHost(
        navController = navController,
        startDestination = BottomScreens.VacinasScreen.route+ "/${Constants.PARAM_PET_ID}"
    ) {
        composable(
            BottomScreens.VacinasScreen.route + "/${Constants.PARAM_PET_ID}",
            arguments = listOf(navArgument(Constants.PARAM_PET_ID) {
                type = NavType.StringType
                defaultValue = petId
            })
        ) {
            val viewModel = hiltViewModel<VacinaViewModel>()
            val state by viewModel.state.collectAsState()

            VacinasScreen(state = state, onEvent = viewModel::onEvent)
        }

        composable(BottomScreens.VermifugacaoScreen.route + "/${Constants.PARAM_PET_ID}") {

        }
    }

}