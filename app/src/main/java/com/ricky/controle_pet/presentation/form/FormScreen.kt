package com.ricky.controle_pet.presentation.form

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.ricky.controle_pet.R
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import com.ricky.controle_pet.domain.enums.SexoEnum
import com.ricky.controle_pet.presentation.form.components.DateDialog
import com.ricky.controle_pet.presentation.form.components.DropdownCompose
import com.ricky.controle_pet.presentation.form.components.ModalBottomSheetCompose
import com.ricky.controle_pet.presentation.form.components.TextFieldCompose
import com.ricky.controle_pet.presentation.vacinas.components.DialogRemoverMedicamento
import com.ricky.controle_pet.utils.getTempUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    state: FormState,
    navController: NavController,
    onEvent: (FormEvent) -> Unit
) {
    val context = LocalContext.current
    val tempUri = remember { mutableStateOf<Uri?>(null) }
    val focusManager = LocalFocusManager.current
    val photoPicker =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = {
                onEvent(FormEvent.SelectPhoto(it, context))
            })

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                tempUri.value?.let {
                    onEvent(FormEvent.SelectPhoto(it, context))
                }
            }
        }
    )

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                tempUri.value = context.getTempUri()
                tempUri.value?.let {
                    cameraLauncher.launch(it)
                }
            } else {
                Toast.makeText(context, "camera permission is denied", Toast.LENGTH_SHORT).show()
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

    if (state.isShowDialogRemover) {
        DialogRemoverMedicamento(
            onDimiss = { onEvent(FormEvent.ShowDialogRemover) },
            onRemoverMedicamento = {
                onEvent(FormEvent.DeletePet)
            })
    }

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = {
            Text(
                text = if (state.isUpdate) stringResource(id = R.string.atualizar_animal) else stringResource(
                    id = R.string.cadastrar_animal
                ),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )
        },
            actions = {
                if (state.isUpdate) {
                    IconButton(onClick = { onEvent(FormEvent.ShowDialogRemover) }) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                        )
                    }
                }
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
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 16.dp)
                    .clickable {
                        focusManager.clearFocus()
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
                modifier = Modifier.padding(top = 10.dp),
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
                        vertical = 16.dp,
                        horizontal = 10.dp
                    ),
                shape = RoundedCornerShape(20.dp),
                onClick = { onEvent(FormEvent.AddPet(context)) }) {
                Text(
                    text = stringResource(id = R.string.salvar_animal),
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        if (state.isShowBottomSheet) {
            ModalBottomSheetCompose(
                onDismiss = { onEvent(FormEvent.ShowBottomSheet) },
                onTakePhotoClick = {
                    focusManager.clearFocus()
                    onEvent(FormEvent.ShowBottomSheet)
                    val permission = Manifest.permission.CAMERA
                    if (ContextCompat.checkSelfPermission(
                            context,
                            permission
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        tempUri.value = context.getTempUri()
                        tempUri.value?.let {
                            cameraLauncher.launch(it)
                        }
                    } else {
                        cameraPermissionLauncher.launch(permission)
                    }
                },
                onPhotoGalleryClick = {
                    focusManager.clearFocus()
                    onEvent(FormEvent.ShowBottomSheet)
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                })
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