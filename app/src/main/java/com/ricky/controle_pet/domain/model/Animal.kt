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
    var nome: String = "",
    var especie: EspecieEnum = EspecieEnum.CANINO,
    var pelagem: String = "",
    var raca: String = "",
    var sexo: String = "",
    var porte: PorteEnum = PorteEnum.PEQUENO,
    var idade: String = "",
    var nascimento: LocalDate = LocalDate.now(),
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var foto: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Animal

        if (id != other.id) return false
        if (especie != other.especie) return false
        if (pelagem != other.pelagem) return false
        if (raca != other.raca) return false
        if (sexo != other.sexo) return false
        if (porte != other.porte) return false
        if (idade != other.idade) return false
        if (nascimento != other.nascimento) return false
        if (foto != null) {
            if (other.foto == null) return false
            if (!foto.contentEquals(other.foto)) return false
        } else if (other.foto != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + especie.hashCode()
        result = 31 * result + pelagem.hashCode()
        result = 31 * result + raca.hashCode()
        result = 31 * result + sexo.hashCode()
        result = 31 * result + porte.hashCode()
        result = 31 * result + idade.hashCode()
        result = 31 * result + nascimento.hashCode()
        result = 31 * result + (foto?.contentHashCode() ?: 0)
        return result
    }
}
