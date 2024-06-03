package com.ricky.controle_pet.presentation.home

sealed interface HomeEvent {
    data class OnChangeTheme(val isDark: Boolean) : HomeEvent
}