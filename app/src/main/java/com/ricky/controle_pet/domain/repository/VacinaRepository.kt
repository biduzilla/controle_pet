package com.ricky.controle_pet.domain.repository

import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.VetAndVacina
import kotlinx.coroutines.flow.Flow

interface VacinaRepository : BaseRepository<Vacina> {
    fun getAllVacinaAndVet(): Flow<List<VetAndVacina>>

    suspend fun getVacinaAndVetById(id: String): VetAndVacina?
}