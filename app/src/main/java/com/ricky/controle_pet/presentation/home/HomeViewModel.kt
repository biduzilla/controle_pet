package com.ricky.controle_pet.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.controle_pet.domain.repository.AnimalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val animalRepository: AnimalRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getAnimals()
    }

    private fun getAnimals() {
        viewModelScope.launch {
            animalRepository.getAll().collect { animals ->
                _state.value = _state.value.copy(
                    animais = animals
                )
            }
        }
    }
}