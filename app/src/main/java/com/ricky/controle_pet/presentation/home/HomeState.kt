package com.ricky.controle_pet.presentation.home

import com.ricky.controle_pet.domain.model.Animal

data class HomeState(
    var animal: Animal = Animal(),
    var tela: String = "",
    var isDark: Boolean = false
)
