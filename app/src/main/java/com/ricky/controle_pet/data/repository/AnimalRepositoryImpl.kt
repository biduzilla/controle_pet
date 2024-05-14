package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.AnimalDao
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.repository.AnimalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(private val animalDao: AnimalDao) :
    AnimalRepository {
    override fun getAll(): Flow<List<Animal>> = animalDao.getAll()

    override suspend fun getById(id: String): Animal? = animalDao.getById(id)

    override suspend fun insert(entity: Animal) = animalDao.insert(entity)

    override suspend fun update(entity: Animal) = animalDao.update(entity)

    override suspend fun delete(entity: Animal) = animalDao.delete(entity)

    override suspend fun deleteById(id: String) = animalDao.deleteById(id)
}