package com.ricky.controle_pet.presentation.home

import com.ricky.controle_pet.domain.model.Animal

data class HomeState(
    var animais: List<Animal> = emptyList()
)
