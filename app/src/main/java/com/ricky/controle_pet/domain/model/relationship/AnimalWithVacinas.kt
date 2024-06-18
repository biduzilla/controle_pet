package com.ricky.controle_pet.domain.model.relationship

import androidx.room.Embedded
import androidx.room.Relation
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.Vacina

data class AnimalWithVacinas(
    @Embedded val animal: Animal,
    @Relation(
        parentColumn = "id",
        entityColumn = "animalId"
    )
    var vacinas: List<Vacina> = emptyList()
)