package com.ricky.controle_pet.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

interface AnimalRepository:BaseRepository<Animal> {
}