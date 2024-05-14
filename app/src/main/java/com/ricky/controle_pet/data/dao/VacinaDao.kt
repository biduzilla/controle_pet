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
interface VacinaDao {
    @Query("SELECT * FROM Vacina")
    fun getAllVacina(): Flow<List<VacinaAndVet>>

    @Query("SELECT * FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun getVacinaById(vacinaId: String): VacinaAndVet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacina(vacina: Vacina)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVacina(vacina: Vacina)

    @Delete
    suspend fun deleteVacina(vacina: Vacina)

    @Query("DELETE FROM Vacina WHERE vacinaId = :vacinaId")
    suspend fun deleteVacinaById(vacinaId: String)

}