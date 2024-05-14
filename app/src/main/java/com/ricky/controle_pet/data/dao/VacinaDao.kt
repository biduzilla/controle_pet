package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VacinaAndVet
import kotlinx.coroutines.flow.Flow

@Dao
interface VacinaDao : BaseDao<Vacina> {
    @Query("SELECT * FROM Vacina")
    fun getAllVacinaAndVet(): Flow<List<VacinaAndVet>>

    @Query("SELECT * FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun getByVacinaAndVetId(vacinaId: String): VacinaAndVet?

    @Query("SELECT * FROM Vacina")
    fun getAll(): Flow<List<Vacina>>

    @Query("SELECT * FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun getById(vacinaId: String): Vacina?

    @Query("DELETE FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun deleteById(vacinaId: String)

}