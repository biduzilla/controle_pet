package com.ricky.controle_pet.presentation.vacinas.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ricky.controle_pet.R
import com.ricky.controle_pet.presentation.form.components.DateDialog
import com.ricky.controle_pet.presentation.form.components.TextFieldCompose
import com.ricky.controle_pet.presentation.vacinas.VacinaState

@Composable
fun DialogForm(
    modifier: Modifier = Modifier,
    state: VacinaState,
    onChangeNome: (String) -> Unit,
    onDismiss: () -> Unit,
    onData: (Long) -> Unit,
    onReforco: (Long) -> Unit,
    isReforco: (Boolean) -> Unit,
    onSave: () -> Unit
) {

    var isShowDataDialog by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val keyBoardManager = LocalFocusManager.current

    if (isShowDataDialog) {
        DateDialog(isPassado = state.isReforco,
            onDimiss = { isShowDataDialog = false }) { data ->
            if (state.isReforco) {
                onReforco(data)
            } else {
                onData(data)
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(id = R.string.add_vacina),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                TextFieldCompose(
                    value = state.nome,
                    isError = state.onErrorNome,
                    label = R.string.nome_vacina
                ) {
                    onChangeNome(it)
                }

                Text(
                    text = stringResource(id = R.string.data_aplicacao),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    onClick = {
                        isShowDataDialog = true
                        isReforco(false)
                    }
                ) {
                    Text(
                        text = state.dataString.ifBlank { stringResource(id = R.string.escolher_data) },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Text(
                    text = stringResource(id = R.string.prox_aplicacao),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    elevation = CardDefaults.cardElevation(10.dp),
                    onClick = {
                        isShowDataDialog = true
                        isReforco(true)
                    }
                ) {
                    Text(
                        text = state.reforcoString.ifBlank { stringResource(id = R.string.escolher_data) },
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Button(
                    onClick = {
                        keyBoardManager.clearFocus()
                        if (state.onErrorData || state.onErrorReforco) {
                            Toast.makeText(
                                context,
                                "Escolha uma data",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            onSave()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.salvar),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }
        }
    }

}