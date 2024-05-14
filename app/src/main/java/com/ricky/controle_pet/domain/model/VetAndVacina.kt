package com.ricky.controle_pet.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class VetAndVacina(
    @Embedded val vet: Vet,
    @Relation(
        parentColumn = "id",
        entityColumn = "vetId",
    )
    var vacina: List<Vacina> = emptyList(),
)
