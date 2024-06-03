package com.ricky.controle_pet.presentation.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit
) {

    val navControllerBottom = rememberNavController()

}