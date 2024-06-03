package com.ricky.controle_pet.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ricky.controle_pet.presentation.form.FormScreen
import com.ricky.controle_pet.presentation.form.FormViewModel
import com.ricky.controle_pet.presentation.home.HomeScreen
import com.ricky.controle_pet.presentation.home.HomeViewModel
import com.ricky.controle_pet.presentation.pets.PetsScreen
import com.ricky.controle_pet.presentation.pets.PetsViewModel
import com.ricky.controle_pet.presentation.splash.SplashScreen
import com.ricky.controle_pet.presentation.splash.SplashViewModel
import com.ricky.controle_pet.utils.Constants

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNav() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composableSlideHorizontally(Screens.SplashScreen.route) {
            val viewModel = hiltViewModel<SplashViewModel>()
            val state by viewModel.state.collectAsState()

            SplashScreen(state = state, navController = navController)
        }

        composableSlideHorizontally(Screens.PetsScreen.route) {
            val viewModel = hiltViewModel<PetsViewModel>()
            val state by viewModel.state.collectAsState()

            PetsScreen(state = state, navController = navController)
        }

        composableSlideHorizontally(Screens.HomeScreen.route + "/{${Constants.PARAM_PET_ID}}") {
            val viewmodel = hiltViewModel<HomeViewModel>()
            val state by viewmodel.state.collectAsState()

            HomeScreen(state = state, navController = navController, viewmodel::onEvent)
        }



        composableSlideHorizontally(Screens.FormScreen.route) {
            val viewModel = hiltViewModel<FormViewModel>()
            val state by viewModel.state.collectAsState()

            FormScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
        }
    }

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.composableSlideHorizontally(
    route: String,
    duration: Int = 400, // 1000 - 400
    enterOffset: Int = 700, // 300 - 1000
    exitOffset: Int = -700,
    popEnterOffset: Int = -700,
    popExitOffset: Int = 700,
    screen: @Composable () -> Unit
) {
    composable(
        route = route,
        enterTransition = { slideInHorizontally(tween(duration)) { enterOffset } },
        exitTransition = { slideOutHorizontally(tween(duration)) { exitOffset } },
        popEnterTransition = { slideInHorizontally(tween(duration)) { popEnterOffset } },
        popExitTransition = { slideOutHorizontally(tween(duration)) { popExitOffset } },
        content = { screen() }
    )
}