package com.ricky.controle_pet.data.converters

import androidx.room.TypeConverter
import com.ricky.controle_pet.domain.enums.EspecieEnum
import com.ricky.controle_pet.domain.enums.PorteEnum
import java.math.BigDecimal
import java.time.LocalDate

class Converters {

    @TypeConverter
    fun fromEspecieEnum(especie: EspecieEnum): String = especie.name

    @TypeConverter
    fun fromPorteEnum(porte: PorteEnum): String = porte.name

    @TypeConverter
    fun toEspecieEnum(value: String): EspecieEnum = enumValueOf(value)

    @TypeConverter
    fun toPorteEnum(value: String): PorteEnum = enumValueOf(value)

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal?): String? = value?.toString()

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal? = value?.let { BigDecimal(it) }

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Long? = date?.toEpochDay()

    @TypeConverter
    fun toLocalDate(value: Long?): LocalDate? = value?.let { LocalDate.ofEpochDay(it) }

}