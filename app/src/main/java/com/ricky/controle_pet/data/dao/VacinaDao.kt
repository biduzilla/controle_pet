package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VetAndVacina
import kotlinx.coroutines.flow.Flow

@Dao
interface VacinaDao : BaseDao<Vacina> {
    @Transaction
    @Query("SELECT * FROM Vacina INNER JOIN Vet ON Vacina.vetId = Vet.id")
    fun getAllVacinaAndVet(): Flow<List<VetAndVacina>>

    @Transaction
    @Query("SELECT * FROM Vacina INNER JOIN Vet ON Vacina.vetId = Vet.id WHERE Vacina.id = :id")
    suspend fun getByVacinaAndVetId(id: String): VetAndVacina?

    @Query("SELECT * FROM Vacina")
    fun getAll(): Flow<List<Vacina>>

    @Query("SELECT * FROM Vacina WHERE id = :id")
    suspend fun getById(id: String): Vacina?

    @Transaction
    @Query("DELETE FROM Vacina WHERE id = :id")
    suspend fun deleteById(id: String)

}