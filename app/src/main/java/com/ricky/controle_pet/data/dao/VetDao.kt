package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface VetDao {
    @Query("SELECT * FROM Vet")
    fun getAllVet(): Flow<List<Vet>>

    @Query("SELECT * FROM Vet WHERE vetId = :vetId")
    suspend fun getVetById(vetId: String): Vet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVet(vet: Vet)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVet(vet: Vet)

    @Delete
    suspend fun deleteVet(vet: Vet)

    @Query("DELETE FROM Vet WHERE vetId = :vetId")
    suspend fun deleteVetById(vetId: String)

}