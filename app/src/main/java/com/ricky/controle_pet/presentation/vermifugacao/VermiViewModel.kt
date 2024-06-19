package com.ricky.controle_pet.presentation.vermifugacao

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.NotificacaoInfo
import com.ricky.controle_pet.domain.model.Vermifugacao
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.domain.repository.VermifugacaoRepository
import com.ricky.controle_pet.utils.Constants
import com.ricky.controle_pet.utils.dataParaLongEspeficica
import com.ricky.controle_pet.utils.formatterLocalDate
import com.ricky.controle_pet.utils.notification.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class VermiViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notificationService: NotificationService,
    private val vermifugacaoRepository: VermifugacaoRepository,
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VermiState())
    val state = _state.asStateFlow()
    private lateinit var petId: String
    private lateinit var pet: Animal

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            this.petId = petId
            getVermi(petId)
            getAnimal(petId)
        }
    }

    private fun getVermi(petId: String) {
        viewModelScope.launch {
            vermifugacaoRepository.getByAnimalIdOrderByData(petId).collect { data ->
                _state.value = _state.value.copy(
                    verm = data
                )
            }
        }

        viewModelScope.launch {
            vermifugacaoRepository.getByAnimalIdWithReforcoAfter(petId).collect { data ->
                _state.value = _state.value.copy(
                    vermNaoAplicadas = data
                )
            }
        }
    }

    private fun getAnimal(petId: String) {
        viewModelScope.launch {
            animalRepository.getById(petId)?.let { animal ->
                pet = animal
            }
        }
    }

    fun onEvent(event: VermiEvent) {
        when (event) {
            is VermiEvent.IsSelectReforco -> {
                _state.value = _state.value.copy(
                    isReforco = event.isProximaVacina
                )
            }
            is VermiEvent.OnChangeData -> {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = event.data
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                val localDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                _state.value = _state.value.copy(
                    data = localDate,
                    dataString = formatterLocalDate(localDate),
                    onErrorData = false
                )
            }

            is VermiEvent.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    onErrorNome = event.nome.trim().isBlank()
                )
            }
            is VermiEvent.OnChangePeso -> {
                _state.value = _state.value.copy(
                    peso = event.peso,
                    onErrorNome = false
                )
            }
            is VermiEvent.OnChangeReforco -> {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = event.reforco
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                val localDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                _state.value = _state.value.copy(
                    reforco = localDate,
                    reforcoString = formatterLocalDate(localDate),
                    onErrorReforco = false
                )
            }
            is VermiEvent.OnDelete -> {
                viewModelScope.launch {
                    vermifugacaoRepository.deleteById(event.id)
                }
            }
            VermiEvent.OnDismissDialogForm -> {
                _state.value = _state.value.copy(
                    isShowDialogForm = false,
                )
            }
            VermiEvent.OnSave -> {
                val verm = Vermifugacao(
                    data = _state.value.data,
                    nome = _state.value.nome,
                    reforco = _state.value.reforco,
                    peso = _state.value.peso,
                    animalId = petId
                )

                agendarAlerta(
                    data = _state.value.reforco,
                    nome = pet.nome
                )
                viewModelScope.launch {
                    vermifugacaoRepository.insert(verm)
                    _state.value = VermiState()
                }
            }
            VermiEvent.OnShowDialogForm -> {
                _state.value = _state.value.copy(
                    isShowDialogForm = true,
                )
            }
        }
    }

    private fun agendarAlerta(
        nome: String,
        data: LocalDate
    ) {
        val not = NotificacaoInfo(
            "Vermifugação",
            "Hoje está marcado a vermifugação do(a) $nome",
            0
        )

        data.dataParaLongEspeficica(
            dia = not.diasAntes,
            hora = 8
        ).apply {
            notificationService.scheduleNotification(
                date = this,
                title = not.titulo,
                message = not.mensagem
            )
        }
    }
}
