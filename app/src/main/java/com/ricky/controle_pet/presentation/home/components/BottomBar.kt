package com.ricky.controle_pet.presentation.home.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ricky.controle_pet.navigation.BottomScreens

@Composable
fun BottomBar(
    navController: NavController,
    petId: String,
    onChangeTela: (String) -> Unit
) {
    val screens = listOf(
        BottomScreens.VacinasScreen,
        BottomScreens.VermifugacaoScreen,
    )

    NavigationBar {
        contentColorFor(backgroundColor = MaterialTheme.colorScheme.primary)
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        screens.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute?.split("/")
                    ?.get(0) == screen.route,
                label = { Text(text = screen.label) },
                icon = {
                    Icon(
                        imageVector = if (currentRoute?.split("/")
                                ?.get(0) == screen.route
                        ) screen.selectedIcon else screen.unselectedIcon,
                        contentDescription = screen.label
                    )
                },
                onClick = {
                    when {
                        screen.label.contains("Vacinas") -> onChangeTela("Vacinas")
                        screen.label.contains("Vermifugações") -> onChangeTela("Vermifugações")
                    }
                    navController.navigate(screen.route + "/$petId") {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}