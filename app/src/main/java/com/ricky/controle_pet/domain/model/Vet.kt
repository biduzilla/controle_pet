package com.ricky.controle_pet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Vet(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var crmv: String = "",
    var nome: String = "",
)