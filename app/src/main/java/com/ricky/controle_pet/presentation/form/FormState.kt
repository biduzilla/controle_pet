package com.ricky.controle_pet.presentation.form

import android.graphics.Bitmap
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import com.ricky.controle_pet.domain.enums.SexoEnum
import java.time.LocalDate

data class FormState(
    var isUpdate:Boolean = false,
    var nome: String = "",
    var especie: EspecieEnum = EspecieEnum.CANINO,
    var pelagem: String = "",
    var raca: String = "",
    var porte: PorteEnum = PorteEnum.PEQUENO,
    var idade: String = "",
    var nascimento: LocalDate = LocalDate.now(),
    var foto: Bitmap? = null,
    var sexo:SexoEnum = SexoEnum.MACHO,
    var isShowDataPicker: Boolean = false,
    var isShowBottomSheet: Boolean = false,
    var isShowDialogRemover: Boolean = false,
    var onErrorNome: Boolean = false,
    var onErrorPelagem: Boolean = false,
    var onErrorRaca: Boolean = false,
    var onErrorNascimento: Boolean = false,
    var onErrorPhoto: Boolean = false,
    var isOk: Boolean = false
)
