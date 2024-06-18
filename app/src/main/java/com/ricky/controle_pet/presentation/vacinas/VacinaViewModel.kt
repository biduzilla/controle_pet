package com.ricky.controle_pet.presentation.vacinas

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.domain.repository.VacinaRepository
import com.ricky.controle_pet.utils.Constants
import com.ricky.controle_pet.utils.notification.NotificationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacinaViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val notificationService: NotificationService,
    private val vacinaRepository: VacinaRepository,
    private val animalRepository: AnimalRepository
) : ViewModel() {
    private val _state = MutableStateFlow(VacinaState())
    private val state = _state.asStateFlow()
    private lateinit var petId: String

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

    fun onEvent(event: VacinaEvent) {
        when (event) {
            is VacinaEvent.IsSelectProxVacina -> {

            }

            is VacinaEvent.OnChangeData -> {

            }

            is VacinaEvent.OnChangeDescricao -> {

            }

            is VacinaEvent.OnChangeNome -> {

            }

            is VacinaEvent.OnChangeProxData -> {

            }

            is VacinaEvent.OnDeleteVacina -> {

            }

            VacinaEvent.OnDismissDialogData -> {

            }

            VacinaEvent.OnDismissDialogForm -> {

            }

            VacinaEvent.OnSave -> {

            }

            VacinaEvent.OnShowDialogData -> {

            }

            VacinaEvent.OnShowDialogForm -> {

            }
        }
    }
}