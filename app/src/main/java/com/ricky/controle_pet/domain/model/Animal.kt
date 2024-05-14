package com.ricky.controle_pet.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import java.time.LocalDate
import java.util.UUID

@Entity
data class Animal(
    @PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var especie: EspecieEnum = EspecieEnum.CANINO,
    var pelagem: String = "",
    var raca: String = "",
    var sexo:String = "",
    var porte:PorteEnum = PorteEnum.PEQUENO,
    var idade:String = "",
    var nascimento:LocalDate = LocalDate.now(),
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var foto:ByteArray? = null
    )
