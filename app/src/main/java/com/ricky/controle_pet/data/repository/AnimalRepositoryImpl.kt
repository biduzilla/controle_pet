package com.ricky.controle_pet.data.repository

import com.ricky.controle_pet.data.dao.AnimalDao
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.relationship.AnimalWithVacinas
import com.ricky.controle_pet.domain.model.relationship.AnimalWithVermifugacao
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.domain.repository.VacinaRepository
import com.ricky.controle_pet.domain.repository.VermifugacaoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val animalDao: AnimalDao,
    private val vermifugacaoRepository: VermifugacaoRepository,
    private val vacinaRepository: VacinaRepository
) :
    AnimalRepository {
    override suspend fun getAnimalWithVacinasById(id: String): AnimalWithVacinas? =
        animalDao.getAnimalWithVacinasById(id)

    override suspend fun getAnimalWithVermifugacaoById(id: String): AnimalWithVermifugacao? =
        animalDao.getAnimalWithVermifugacaoById(id)

    override fun getAll(): Flow<List<Animal>> = animalDao.getAll()

    override suspend fun getById(id: String): Animal? = animalDao.getById(id)

    override suspend fun insert(entity: Animal) = animalDao.insert(entity)

    override suspend fun update(entity: Animal) = animalDao.update(entity)

    override suspend fun delete(entity: Animal) = animalDao.delete(entity)

    override suspend fun deleteById(id: String) {
        getAnimalWithVacinasById(id)?.let {
            vacinaRepository.deleteList(it.vacinas)
        }

        getAnimalWithVermifugacaoById(id)?.let {
            vermifugacaoRepository.deleteList(it.vermifugacoes)
        }
        deleteById(id)
    }

    override suspend fun deleteList(list: List<Animal>) = animalDao.deleteList(list)
}