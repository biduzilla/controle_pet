package com.ricky.controle_pet.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity
data class Vacina(
    @PrimaryKey
    var id:String = UUID.randomUUID().toString(),
    var data:LocalDate = LocalDate.now(),
    var nome:String = "",
    var reforco:LocalDate = LocalDate.now(),
    var animalId:String = ""
)
