package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao:BaseDao<Animal> {
    @Query("SELECT * FROM Animal")
    fun getAll(): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE animalId = :animalId")
    suspend fun getById(animalId: String): Animal?

    @Query("DELETE FROM Animal WHERE animalId = :animalId")
    suspend fun deleteById(animalId: String)
}