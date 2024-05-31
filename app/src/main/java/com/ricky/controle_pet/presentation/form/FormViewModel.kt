package com.ricky.controle_pet.presentation.form

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.utils.bitmapToByteArray
import com.ricky.controle_pet.utils.calculateAgeAndMonths
import com.ricky.controle_pet.utils.uriToBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    context: Context,
    private val animalRepository: AnimalRepository
) :
    ViewModel() {
    private val _state = MutableStateFlow(FormState())
    val state = _state.asStateFlow()

    fun onEvent(event: FormEvent) {
        when (event) {
            is FormEvent.AddPet -> {
                if (_state.value.nome.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorNome = true
                    )
                    return
                }

                if (_state.value.pelagem.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorPelagem = true
                    )
                    return
                }

                if (_state.value.raca.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorRaca = true
                    )
                    return
                }
                if (_state.value.idade.trim().isBlank()) {
                    _state.value = _state.value.copy(
                        onErrorNascimento = true
                    )
                    return
                }

                if (_state.value.foto == null) {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true
                    )
                    return
                }

                val animal = Animal(
                    nome = _state.value.nome,
                    especie = _state.value.especie,
                    pelagem = _state.value.pelagem,
                    raca = _state.value.raca,
                    sexo = _state.value.sexo,
                    porte = _state.value.porte,
                    nascimento = _state.value.nascimento,
                    foto = bitmapToByteArray(_state.value.foto!!)
                )

                viewModelScope.launch {
                    animalRepository.insert(animal)
                    _state.value = _state.value.copy(
                        isOk = true
                    )
                }
            }

            is FormEvent.OnChangeEspecie -> {
                _state.value = _state.value.copy(
                    especie = event.especie
                )
            }

            is FormEvent.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    onErrorNome = false
                )
            }

            is FormEvent.OnChangePelagem -> {
                _state.value = _state.value.copy(
                    pelagem = event.pelagem,
                    onErrorPelagem = false
                )
            }

            is FormEvent.OnChangePorte -> {
                _state.value = _state.value.copy(
                    porte = event.porte,
                )
            }

            is FormEvent.OnChangeRaca -> {
                _state.value = _state.value.copy(
                    raca = event.raca,
                    onErrorRaca = false
                )
            }

            is FormEvent.SelectPhoto -> {
                event.uri?.let {
                    _state.value = _state.value.copy(
                        uri = event.uri,
                        foto = uriToBitmap(uri = event.uri, context = event.context),
                        onErrorPhoto = false
                    )
                } ?: run {
                    _state.value = _state.value.copy(
                        onErrorPhoto = true
                    )
                }
            }

            FormEvent.ShowDataPicker -> {
                _state.value = _state.value.copy(
                    isShowDataPicker = !_state.value.isShowDataPicker
                )
            }

            is FormEvent.OnChangeSexo -> {
                _state.value = _state.value.copy(
                    sexo = event.sexo,
                )
            }

            is FormEvent.OnChangeDate -> {
                val calendar = Calendar.getInstance()
                calendar.time = Date(event.date)
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                _state.value = _state.value.copy(
                    nascimento = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                    idade = calculateAgeAndMonths(LocalDate.ofEpochDay(event.date)),
                    onErrorNascimento = false
                )
            }

            FormEvent.ShowBottomSheet -> {
                _state.value = _state.value.copy(
                    isShowBottomSheet = !_state.value.isShowBottomSheet
                )
            }

        }
    }
}