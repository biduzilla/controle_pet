package com.ricky.controle_pet.domain.repository

import com.ricky.controle_pet.domain.model.Vermifugacao
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface VermifugacaoRepository:BaseRepository<Vermifugacao> {
    fun getByAnimalIdOrderByData(id: String): Flow<List<Vermifugacao>>

    fun getByAnimalIdWithReforcoAfter(
        id: String,
        currentDate: LocalDate = LocalDate.now()
    ): Flow<List<Vermifugacao>>
}