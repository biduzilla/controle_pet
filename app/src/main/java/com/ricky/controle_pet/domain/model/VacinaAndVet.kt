package com.ricky.controle_pet.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class VacinaAndVet(
    @Embedded val vet: Vet,
    @Relation(
        parentColumn = "id",
        entityColumn = "vetId"
    )
    val vacina: Vacina
)
