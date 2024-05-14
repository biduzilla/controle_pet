package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Animal
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao:BaseDao<Animal> {
    @Query("SELECT * FROM Animal")
    fun getAll(): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE id = :id")
    suspend fun getById(id: String): Animal?

    @Transaction
    @Query("DELETE FROM Animal WHERE id = :id")
    suspend fun deleteById(id: String)
}