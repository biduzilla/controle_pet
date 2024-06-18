package com.ricky.controle_pet.presentation.vacinas

sealed interface VacinaEvent {
    data object OnShowDialogForm : VacinaEvent
    data object OnDismissDialogForm : VacinaEvent
    data object OnSave : VacinaEvent
    data class OnDeleteVacina(val id: String) : VacinaEvent
    data class IsSelectProxVacina(val isProximaVacina: Boolean) : VacinaEvent
    data class OnChangeNome(val nome: String) : VacinaEvent
    data class OnChangeData(val data: Long) : VacinaEvent
    data class OnChangeProxData(val proxData: Long) : VacinaEvent

}