package com.ricky.controle_pet.presentation.vacinas

import com.ricky.controle_pet.domain.model.Vacina
import java.time.LocalDate

data class VacinaState(
    var vacinaNaoAplicadas: List<Vacina> = emptyList(),
    var vacinas: List<Vacina> = emptyList(),
    var isShowDialogForm: Boolean = false,
    var isShowDialogeData: Boolean = false,
    var isReforco: Boolean = false,
    var nome: String = "",
    var data: LocalDate = LocalDate.now(),
    var dataString: String = "",
    var reforco: LocalDate = LocalDate.now(),
    var reforcoString: String = "",
    var onErrorNome: Boolean = false,
    var onErrorData: Boolean = false,
    var onErrorReforco: Boolean = false,

)
