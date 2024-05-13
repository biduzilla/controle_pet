package com.ricky.controle_pet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
data class Vermifugacao(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var nome: String = "",
    var peso: BigDecimal = BigDecimal.ZERO,
    var reforco: LocalDate = LocalDate.now()
)