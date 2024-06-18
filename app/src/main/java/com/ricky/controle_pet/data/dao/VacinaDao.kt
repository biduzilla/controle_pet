package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Vacina
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface VacinaDao : BaseDao<Vacina> {

    @Query("SELECT * FROM Vacina")
    fun getAll(): Flow<List<Vacina>>

    @Query("SELECT * FROM Vacina WHERE id = :id")
    suspend fun getById(id: String): Vacina?

    @Query("SELECT * FROM Vacina WHERE animalId = :id ORDER BY data")
     fun getByAnimalIdOrderByData(id: String):  Flow<List<Vacina>>

    @Query("SELECT * FROM Vacina WHERE animalId = :id AND reforco > :currentDate")
     fun getByAnimalIdWithReforcoAfter(
        id: String,
        currentDate: LocalDate = LocalDate.now()
    ):  Flow<List<Vacina>>

    @Transaction
    @Query("DELETE FROM Vacina WHERE id = :id")
    suspend fun deleteById(id: String)

}