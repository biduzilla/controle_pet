package com.ricky.controle_pet.presentation.form

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.controle_pet.R
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import com.ricky.controle_pet.domain.enums.SexoEnum
import com.ricky.controle_pet.presentation.form.components.DateDialog
import com.ricky.controle_pet.presentation.form.components.DropdownCompose
import com.ricky.controle_pet.presentation.form.components.TextFieldCompose

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    state: FormState,
    navController: NavController,
    onEvent: (FormEvent) -> Unit
) {
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()
    val photoPicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onEvent(FormEvent.SelectPhoto(it, context))
            })

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {

            } else {

            }
        }

    if (state.onErrorPhoto) {
        Toast.makeText(context, "Escolha uma foto", Toast.LENGTH_SHORT).show()
    }
    if (state.onErrorNascimento) {
        Toast.makeText(context, "Escolha uma data de nascimento", Toast.LENGTH_SHORT).show()
    }
    if (state.isOk) {
        navController.popBackStack()
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = stringResource(id = R.string.cadastrar_animal),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = null,
                    )
                }
            })
    }) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
                    .clickable {
                        onEvent(FormEvent.ShowBottomSheet)
                    },
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                if (state.foto == null) {
                    Icon(
                        imageVector = Icons.Default.AddAPhoto,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    )
                } else {
                    Image(
                        bitmap = state.foto!!.asImageBitmap(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            TextFieldCompose(
                value = state.nome,
                isError = state.onErrorNome,
                label = R.string.nome_pet
            ) {
                onEvent(FormEvent.OnChangeNome(it))
            }
            DropdownCompose(
                label = R.string.especie,
                value = state.especie.value,
                list = EspecieEnum.entries.toTypedArray()
            ) {
                onEvent(FormEvent.OnChangeEspecie(it))
            }

            DropdownCompose(
                label = R.string.sexo,
                value = state.sexo.value,
                list = SexoEnum.entries.toTypedArray()
            ) {
                onEvent(FormEvent.OnChangeSexo(it))
            }

            TextFieldCompose(
                value = state.pelagem,
                isError = state.onErrorPelagem,
                label = R.string.pelagem
            ) {
                onEvent(FormEvent.OnChangePelagem(it))
            }
            TextFieldCompose(
                value = state.raca,
                isError = state.onErrorRaca,
                label = R.string.raca
            ) {
                onEvent(FormEvent.OnChangeRaca(it))
            }
            DropdownCompose(
                label = R.string.tamanho,
                value = state.porte.value,
                list = PorteEnum.entries.toTypedArray()
            ) {
                onEvent(FormEvent.OnChangePorte(it))
            }

            Card(
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(10.dp),
                onClick = {
                    onEvent(FormEvent.ShowDataPicker)
                },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = state.idade.ifBlank { stringResource(id = R.string.data_nascimento) },
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (state.isShowDataPicker) {
                DateDialog(isPassado = true,
                    onDimiss = { onEvent(FormEvent.ShowDataPicker) },
                    onChangeDate = { onEvent(FormEvent.OnChangeDate(it)) })
            }

            Button(
                modifier = Modifier
                    .padding(
                        vertical = 4.dp,
                        horizontal = 8.dp
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = { onEvent(FormEvent.AddPet(context)) }) {
                Text(
                    text = stringResource(id = R.string.salvar_animal),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        if (state.isShowBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { onEvent(FormEvent.ShowBottomSheet) },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {

                            },
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = stringResource(id = R.string.camera),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .clickable {
                                photoPicker.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            },
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            modifier = Modifier.padding(vertical = 4.dp),
                            imageVector = Icons.Default.Image,
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = stringResource(id = R.string.galeria),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))
                }

            }
        }
    }
}

@Preview
@Composable
private fun FormScreenPreview() {
    val context = LocalContext.current
    FormScreen(state = FormState(), navController = NavController(context)) {
    }
}