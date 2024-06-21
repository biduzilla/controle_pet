package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.VacinaDao
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.repository.VacinaRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class VacinaRepositoryImpl @Inject constructor(private val vacinaDao: VacinaDao) :
    VacinaRepository {
    override fun getByAnimalIdOrderByData(id: String): Flow<List<Vacina>> =
        vacinaDao.getByAnimalIdOrderByData(id)

    override fun getByAnimalIdWithReforcoAfter(
        id: String,
        currentDate: LocalDate
    ): Flow<List<Vacina>> =
        vacinaDao.getByAnimalIdWithReforcoAfter(id, currentDate)

    override fun getAll(): Flow<List<Vacina>> = vacinaDao.getAll()

    override suspend fun getById(id: String): Vacina? = vacinaDao.getById(id)

    override suspend fun insert(entity: Vacina) = vacinaDao.insert(entity)

    override suspend fun update(entity: Vacina) = vacinaDao.update(entity)

    override suspend fun delete(entity: Vacina) = vacinaDao.delete(entity)

    override suspend fun deleteById(id: String) = vacinaDao.deleteById(id)
    override suspend fun deleteList(list: List<Vacina>) {
        return vacinaDao.deleteList(list)
    }

}