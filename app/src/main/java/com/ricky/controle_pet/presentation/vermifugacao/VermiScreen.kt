package com.ricky.controle_pet.presentation.vermifugacao

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
import com.ricky.controle_pet.presentation.vermifugacao.components.CardVerm
import com.ricky.controle_pet.presentation.vermifugacao.components.DialogVermForm
import com.ricky.controle_pet.presentation.vermifugacao.components.EventoVermCompose

@Composable
fun VermifugacaoScreen(
    state: VermiState,
    onEvent: (VermiEvent) -> Unit
) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(shape = CircleShape,
            modifier = Modifier
                .size(112.dp)
                .padding(16.dp),
            onClick = { onEvent(VermiEvent.OnShowDialogForm) }) {
            Icon(Icons.Default.Vaccines, null)
        }
    }) { paddingValues ->
        if (state.isShowDialogForm) {
            DialogVermForm(
                state = state,
                onChangeNome = { onEvent(VermiEvent.OnChangeNome(it)) },
                onDismiss = { onEvent(VermiEvent.OnDismissDialogForm) },
                onData = { onEvent(VermiEvent.OnChangeData(it)) },
                onReforco = { onEvent(VermiEvent.OnChangeReforco(it)) },
                isReforco = { onEvent(VermiEvent.IsSelectReforco(it)) },
                onChangePeso = {onEvent(VermiEvent.OnChangePeso(it))},
                onSave = { onEvent(VermiEvent.OnSave) }
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
            items(state.vermNaoAplicadas) { item ->
                EventoVermCompose(evento = item)
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = R.string.vacina_aplicada),
                    style = MaterialTheme.typography.headlineLarge,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(state.verm) { item ->
                CardVerm(
                    vermifugacao = item,
                    onRemoverMedicamento = {
                        onEvent(VermiEvent.OnDelete(item.id))
                    })
            }
        }
    }
}