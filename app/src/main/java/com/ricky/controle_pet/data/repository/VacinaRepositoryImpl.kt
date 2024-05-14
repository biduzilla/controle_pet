package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.VacinaDao
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VacinaAndVet
import com.ricky.controle_pet.domain.repository.VacinaRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VacinaRepositoryImpl @Inject constructor(private val vacinaDao: VacinaDao) :
    VacinaRepository {
    override fun getAllVacina(): Flow<List<VacinaAndVet>> = vacinaDao.getAllVacina()

    override suspend fun getVacinaById(vacinaId: String): VacinaAndVet =
        vacinaDao.getVacinaById(vacinaId)

    override suspend fun insertVacina(vacina: Vacina) = vacinaDao.insertVacina(vacina)

    override suspend fun updateVacina(vacina: Vacina) = vacinaDao.updateVacina(vacina)

    override suspend fun deleteVacina(vacina: Vacina) = vacinaDao.deleteVacina(vacina)

    override suspend fun deleteVacinaById(vacinaId: String) = vacinaDao.deleteVacinaById(vacinaId)
}