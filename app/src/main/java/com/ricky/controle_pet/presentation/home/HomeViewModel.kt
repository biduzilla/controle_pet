package com.ricky.controle_pet.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.data.DataStoreUtil
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val animalRepository: AnimalRepository,
    private val dataStoreUtil: DataStoreUtil,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_PET_ID)?.let {
            getAnimal(it)
        }
    }

    private fun getAnimal(id: String) {
        viewModelScope.launch {
            animalRepository.getById(id)?.let {
                _state.value = _state.value.copy(
                    animal = it
                )
            }
        }
    }
}