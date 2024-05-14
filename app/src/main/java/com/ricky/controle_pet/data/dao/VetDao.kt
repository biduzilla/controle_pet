package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface VetDao:BaseDao<Vet> {
    @Query("SELECT * FROM Vet")
    fun getAll(): Flow<List<Vet>>

    @Query("SELECT * FROM Vet WHERE id = :id")
    suspend fun getById(id: String): Vet?

    @Transaction
    @Query("DELETE FROM Vet WHERE id = :id")
    suspend fun deleteById(id: String)

}