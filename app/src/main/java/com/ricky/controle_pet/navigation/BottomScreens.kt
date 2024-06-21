package com.ricky.controle_pet.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.Vaccines
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomScreens(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
) {

    data object VacinasScreen : BottomScreens(
        route = "vacina_screen",
        label = "Vacinas",
        selectedIcon = Icons.Filled.Vaccines,
        unselectedIcon = Icons.Outlined.Vaccines
    )

    data object VermifugacaoScreen : BottomScreens(
        route = "vermifugacao_screen",
        label = "Vermifugações",
        selectedIcon = Icons.Filled.Medication,
        unselectedIcon = Icons.Outlined.Medication
    )
}