package com.ricky.controle_pet.domain.repository

import com.ricky.controle_pet.domain.model.Vacina
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface VacinaRepository : BaseRepository<Vacina> {
    fun getByAnimalIdOrderByData(id: String): Flow<List<Vacina>>

    fun getByAnimalIdWithReforcoAfter(
        id: String,
        currentDate: LocalDate = LocalDate.now()
    ): Flow<List<Vacina>>
}