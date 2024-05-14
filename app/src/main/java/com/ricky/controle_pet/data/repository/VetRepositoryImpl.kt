package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.VetDao
import com.ricky.controle_pet.domain.model.Vet
import com.ricky.controle_pet.domain.repository.VetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VetRepositoryImpl @Inject constructor(private val vetDao: VetDao) : VetRepository {

    override fun getAll(): Flow<List<Vet>> = vetDao.getAll()

    override suspend fun getById(id: String): Vet? = vetDao.getById(id)

    override suspend fun insert(entity: Vet) = vetDao.insert(entity)

    override suspend fun update(entity: Vet) = vetDao.update(entity)

    override suspend fun delete(entity: Vet) = vetDao.delete(entity)

    override suspend fun deleteById(id: String) = vetDao.deleteById(id)
}