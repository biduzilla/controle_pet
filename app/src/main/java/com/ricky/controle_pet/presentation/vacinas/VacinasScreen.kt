package com.ricky.controle_pet.presentation.vacinas

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Vaccines
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ricky.controle_pet.R
import com.ricky.controle_pet.presentation.vacinas.components.CardVacina
import com.ricky.controle_pet.presentation.vacinas.components.DialogVacinaForm
import com.ricky.controle_pet.presentation.vermifugacao.components.CardVerm
import com.ricky.controle_pet.presentation.vermifugacao.components.DialogVermForm
import com.ricky.controle_pet.presentation.vacinas.components.EventoCompose

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
        if (state.isShowDialogForm) {
            DialogVacinaForm(
                state = state,
                onChangeNome = { onEvent(VacinaEvent.OnChangeNome(it)) },
                onDismiss = { onEvent(VacinaEvent.OnDismissDialogForm) },
                onData = { onEvent(VacinaEvent.OnChangeData(it)) },
                onReforco = { onEvent(VacinaEvent.OnChangeProxData(it)) },
                isReforco = { onEvent(VacinaEvent.IsSelectProxVacina(it)) },
                onSave = { onEvent(VacinaEvent.OnSave) }
            )
        }

        LazyColumn(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.prox_vacina),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.vacinaNaoAplicadas) { item ->
                EventoCompose(evento = item)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.vacina_aplicada),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.vacinas) { item ->
                CardVacina(
                    vacina = item,
                    onRemoverMedicamento = {
                        onEvent(VacinaEvent.OnDeleteVacina(item.id))
                    })
            }
        }
    }
}