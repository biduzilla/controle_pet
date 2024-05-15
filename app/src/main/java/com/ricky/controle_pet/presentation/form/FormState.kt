package com.ricky.controle_pet.presentation.form

import android.graphics.Bitmap
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import java.time.LocalDate

data class FormState(
    var nome: String = "",
    var especie: EspecieEnum = EspecieEnum.CANINO,
    var pelage: String = "",
    var raca: String = "",
    var porte: PorteEnum = PorteEnum.PEQUENO,
    var idade: String = "",
    var nascimento: LocalDate = LocalDate.now(),
    var foto: Bitmap? = null,
    var isShowDataPicker: Boolean = false,
    var onErrorNome: Boolean = false,
    var onErrorPelagem: Boolean = false,
    var onErrorRaca: Boolean = false,
    var onErrorNascimento: Boolean = false,
    var onErrorFoto: Boolean = false,
    var isOk: Boolean = false
)
