package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface VetDao:BaseDao<Vet> {
    @Query("SELECT * FROM Vet")
    fun getAll(): Flow<List<Vet>>

    @Query("SELECT * FROM Vet WHERE vetId = :vetId")
    suspend fun getById(vetId: String): Vet?

    @Query("DELETE FROM Vet WHERE vetId = :vetId")
    suspend fun deleteById(vetId: String)

}