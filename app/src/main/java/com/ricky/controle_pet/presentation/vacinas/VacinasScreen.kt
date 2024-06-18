package com.ricky.controle_pet.presentation.vacinas

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VacinasScreen(
    state: VacinaState,
    onEvent: (VacinaEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(shape = CircleShape,
            modifier = Modifier
                .size(112.dp)
                .padding(16.dp),
            onClick = { onEvent(VacinaEvent.OnShowDialogForm) }) {
            Icon(Icons.Default.Vaccines, null)
        }
    }) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {

        }
    }
}