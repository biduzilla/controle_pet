package com.ricky.controle_pet.presentation.vacinas

import com.ricky.controle_pet.domain.model.Vacina
import java.time.LocalDate

data class VacinaState(
    var vacinaNaoAplicadas: List<Vacina> = emptyList(),
    var vacinas: List<Vacina> = emptyList(),
    var isShowDialogForm: Boolean = false,
    var isShowDialogeData: Boolean = false,
    var isProxVacina: Boolean = false,
    var nome: String = "",
    var data: LocalDate = LocalDate.now(),
    var reforco: LocalDate = LocalDate.now(),
    var onErrorNome: Boolean = false,
    var onErrorData: Boolean = false
)
