package com.ricky.controle_pet.presentation.pets

import com.ricky.controle_pet.domain.model.Animal

data class PetsState(
    var animais: List<Animal> = emptyList()
)
