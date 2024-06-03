package com.ricky.controle_pet.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ricky.controle_pet.navigation.BottomNav
import com.ricky.controle_pet.presentation.home.components.BottomBar
import com.ricky.controle_pet.presentation.home.components.TopBarPet

@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit
) {

    val navControllerBottom = rememberNavController()

    Scaffold(topBar = {
        TopBarPet(
            animal = state.animal,
            label = state.tela,
            isDark = state.isDark,
            onVoltar = { navController.popBackStack() },
            onClick = { },
            onChangeTheme = {})
    },
        bottomBar = {
            BottomBar(
                navController = navControllerBottom,
                petId = state.animal.id,
                onChangeTela = {}
            )
        }) { paddingValues ->
        Box(Modifier.padding(paddingValues)) {
            BottomNav(
                navController = navControllerBottom,
                petId = state.animal.id
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreePrev() {
    val context = LocalContext.current
    HomeScreen(state = HomeState(), navController = NavController(context)) {

    }
}