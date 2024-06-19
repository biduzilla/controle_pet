package com.ricky.controle_pet.presentation.vacinas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.NotificacaoInfo
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.domain.repository.VacinaRepository
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
class VacinaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notificationService: NotificationService,
    private val vacinaRepository: VacinaRepository,
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VacinaState())
    val state = _state.asStateFlow()
    private lateinit var petId: String
    private lateinit var pet: Animal

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
            this.petId = petId

            getVacinas(petId)
        }
    }

    private fun getVacinas(petId: String) {
        viewModelScope.launch {
            vacinaRepository.getByAnimalIdOrderByData(petId).collect { vacinas ->
                _state.value = _state.value.copy(
                    vacinas = vacinas
                )
            }
        }

        viewModelScope.launch {
            vacinaRepository.getByAnimalIdWithReforcoAfter(petId).collect { vacinas ->
                _state.value = _state.value.copy(
                    vacinaNaoAplicadas = vacinas
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

    fun onEvent(event: VacinaEvent) {
        when (event) {
            is VacinaEvent.IsSelectProxVacina -> {
                _state.value = _state.value.copy(
                    isProxVacina = event.isProximaVacina
                )
            }

            is VacinaEvent.OnChangeData -> {
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

            is VacinaEvent.OnChangeNome -> {
                _state.value = _state.value.copy(
                    nome = event.nome,
                    onErrorNome = event.nome.trim().isBlank()
                )
            }

            is VacinaEvent.OnChangeProxData -> {
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = event.proxData
                calendar.add(Calendar.DAY_OF_YEAR, 1)

                val localDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

                _state.value = _state.value.copy(
                    reforco = localDate,
                    reforcoString = formatterLocalDate(localDate),
                    onErrorReforco = false
                )
            }

            is VacinaEvent.OnDeleteVacina -> {
                viewModelScope.launch {
                    vacinaRepository.deleteById(event.id)
                }
            }


            VacinaEvent.OnDismissDialogForm -> {
                _state.value = _state.value.copy(
                    isShowDialogForm = false,
                )
            }

            VacinaEvent.OnSave -> {
                val vacina = Vacina(
                    data = _state.value.data,
                    nome = _state.value.nome,
                    reforco = _state.value.reforco,
                    animalId = petId
                )

                agendarAlertaVacina(
                    title = _state.value.nome,
                    data = _state.value.reforco,
                    nome = pet.nome
                )
                viewModelScope.launch {
                    vacinaRepository.insert(vacina)
                    _state.value = VacinaState()
                }
            }


            VacinaEvent.OnShowDialogForm -> {
                _state.value = _state.value.copy(
                    isShowDialogForm = true,
                )
            }
        }
    }

    private fun agendarAlertaVacina(
        title: String,
        nome: String,
        data: LocalDate
    ) {
        val not = NotificacaoInfo(
            "Revacinar - $title",
            "Hoje está marcado a revacinação do(a) $nome da vacina $title",
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