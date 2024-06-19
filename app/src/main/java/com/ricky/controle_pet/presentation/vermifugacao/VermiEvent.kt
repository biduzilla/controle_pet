package com.ricky.controle_pet.presentation.vermifugacao

import java.math.BigDecimal

sealed interface VermiEvent {
    data object OnShowDialogForm : VermiEvent
    data object OnDismissDialogForm : VermiEvent
    data object OnSave : VermiEvent
    data class OnDelete(val id: String) : VermiEvent
    data class IsSelectReforco(val isProximaVacina: Boolean) : VermiEvent
    data class OnChangeNome(val nome: String) : VermiEvent
    data class OnChangePeso(val peso: BigDecimal) : VermiEvent
    data class OnChangeData(val data: Long) : VermiEvent
    data class OnChangeReforco(val reforco: Long) : VermiEvent

}