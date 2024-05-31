package com.ricky.controle_pet.domain.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {
    fun getAll(): Flow<List<T>>
    suspend fun getById(id: String): T?
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
    suspend fun deleteById(id: String)
}
