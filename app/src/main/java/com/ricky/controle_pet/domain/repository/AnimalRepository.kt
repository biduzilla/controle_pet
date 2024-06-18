package com.ricky.controle_pet.domain.repository

import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.relationship.AnimalWithVacinas
import com.ricky.controle_pet.domain.model.relationship.AnimalWithVermifugacao

interface AnimalRepository:BaseRepository<Animal> {
    suspend fun getAnimalWithVacinasById(id: String): AnimalWithVacinas?

    suspend fun getAnimalWithVermifugacaoById(id: String): AnimalWithVermifugacao?
}