package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.Vermifugacao
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface VermifugacaoDao:BaseDao<Vermifugacao> {
    @Query("SELECT * FROM Vermifugacao")
    fun getAllVermifugacao(): Flow<List<Vermifugacao>>

    @Query("SELECT * FROM Vermifugacao WHERE id = :id")
    suspend fun getById(id: String): Vermifugacao?

    @Query("SELECT * FROM Vermifugacao WHERE animalId = :id ORDER BY data")
    fun getByAnimalIdOrderByData(id: String):  Flow<List<Vermifugacao>>

    @Query("SELECT * FROM Vermifugacao WHERE animalId = :id AND reforco > :currentDate")
    fun getByAnimalIdWithReforcoAfter(
        id: String,
        currentDate: LocalDate = LocalDate.now()
    ):  Flow<List<Vermifugacao>>

    @Transaction
    @Query("DELETE FROM Vermifugacao WHERE id = :id")
    suspend fun deleteById(id: String)
}