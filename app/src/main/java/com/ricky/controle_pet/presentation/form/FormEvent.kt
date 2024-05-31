package com.ricky.controle_pet.presentation.form

import android.content.Context
import android.net.Uri
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import com.ricky.controle_pet.domain.enums.SexoEnum

sealed interface FormEvent {

    data object ShowBottomSheet : FormEvent
    data class SelectPhoto(val uri: Uri?,val context: Context) : FormEvent
    data class OnChangeNome(val nome: String) : FormEvent
    data class OnChangePelagem(val pelagem: String) : FormEvent
    data class OnChangeRaca(val raca: String) : FormEvent
    data class OnChangeEspecie(val especie: EspecieEnum) : FormEvent
    data class OnChangeSexo(val sexo: SexoEnum) : FormEvent
    data class OnChangePorte(val porte: PorteEnum) : FormEvent
    data object ShowDataPicker : FormEvent
    data class AddPet(val context: Context) : FormEvent
    data class OnChangeDate(val date: Long) : FormEvent

}
