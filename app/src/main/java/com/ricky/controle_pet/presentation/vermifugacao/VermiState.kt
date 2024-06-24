package com.ricky.controle_pet.presentation.vermifugacao

import com.ricky.controle_pet.domain.model.Vermifugacao
import java.math.BigDecimal
import java.time.LocalDate

data class VermiState(
    var vermNaoAplicadas: List<Vermifugacao> = emptyList(),
    var verm: List<Vermifugacao> = emptyList(),
    var isShowDialogForm: Boolean = false,
    var isReforco: Boolean = false,
    var nome: String = "",
    var peso: BigDecimal = BigDecimal.ZERO,
    var data: LocalDate = LocalDate.now(),
    var dataString: String = "",
    var reforco: LocalDate = LocalDate.now(),
    var reforcoString: String = "",
    var onErrorNome: Boolean = false,
    var onErrorData: Boolean = false,
    var onErrorReforco: Boolean = false,
    var onErrorPeso: Boolean = false,
)
