package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VacinaAndVet
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface VacinaDao:BaseDao<Vacina> {
    @Query("SELECT * FROM Vacina")
    fun getAll(): Flow<List<VacinaAndVet>>

    @Query("SELECT * FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun getById(vacinaId: String): VacinaAndVet?

    @Query("DELETE FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun deleteById(vacinaId: String)

}