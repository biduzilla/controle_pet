package com.ricky.controle_pet.presentation.form

import android.net.Uri
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum

sealed interface FormEvent {

    data class SelectPhoto(val uri: Uri?) : FormEvent
    data class OnChangeNome(val nome: String) : FormEvent
    data class OnChangePelagem(val pelagem: String) : FormEvent
    data class OnChangeRaca(val raca: String) : FormEvent
    data class OnChangeEspecie(val especie: EspecieEnum) : FormEvent
    data class OnChangePorte(val porte: PorteEnum) : FormEvent
    data object ShowDataPicker : FormEvent
    data object AddPet : FormEvent

}
