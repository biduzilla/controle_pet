package com.ricky.controle_pet.presentation.pets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.controle_pet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetsScreen(state: PetsState, navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.meus_animais),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                )
            })
    }, floatingActionButton = {
        FloatingActionButton(modifier = Modifier.padding(16.dp), onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }) { paddingValues ->
        LazyColumn(Modifier.padding(paddingValues)) {
            items(state.animais) { animal ->
            }
        }
    }
}

@Preview
@Composable
private fun PetsScreenPreview() {
    val context = LocalContext.current
    PetsScreen(state = PetsState(), navController = NavController(context))
}