package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.VermifugacaoDao
import com.ricky.controle_pet.domain.model.Vermifugacao
import com.ricky.controle_pet.domain.repository.VermifugacaoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VermifugacaoRepositoryImpl @Inject constructor(private val vermifugacaoDao: VermifugacaoDao) :
    VermifugacaoRepository {
    override fun getAll(): Flow<List<Vermifugacao>>  = vermifugacaoDao.getAllVermifugacao()

    override suspend fun getById(id: String): Vermifugacao? = vermifugacaoDao.getById(id)

    override suspend fun insert(entity: Vermifugacao) = vermifugacaoDao.insert(entity)
    override suspend fun update(entity: Vermifugacao) = vermifugacaoDao.update(entity)

    override suspend fun delete(entity: Vermifugacao) {
        vermifugacaoDao.delete(entity)
    }

    override suspend fun deleteById(id: String) {
        vermifugacaoDao.deleteById(id)
    }
}