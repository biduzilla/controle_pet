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
interface AnimalDao {
    @Query("SELECT * FROM Animal")
    fun getAllAnimais(): Flow<List<Animal>>

    @Query("SELECT * FROM Animal WHERE animalId = :animalId")
    suspend fun getAnimalById(animalId: String): Vet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: Animal)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    @Query("DELETE FROM Animal WHERE animalId = :animalId")
    suspend fun deleteAnimalById(animalId: String)
}