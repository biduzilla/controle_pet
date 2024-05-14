package com.ricky.controle_pet.domain.repository

import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VacinaAndVet
import kotlinx.coroutines.flow.Flow

interface VacinaRepository : BaseRepository<Vacina> {
    fun getAllVacinaAndVet(): Flow<List<VacinaAndVet>>

    suspend fun getVacinaAndVetById(id: String): VacinaAndVet?
}