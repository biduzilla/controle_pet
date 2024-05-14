package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.ricky.controle_pet.domain.model.Vermifugacao
import kotlinx.coroutines.flow.Flow

@Dao
interface VermifugacaoDao:BaseDao<Vermifugacao> {
    @Query("SELECT * FROM Vermifugacao")
    fun getAllVermifugacao(): Flow<List<Vermifugacao>>

    @Query("SELECT * FROM Vermifugacao WHERE id = :id")
    suspend fun getById(id: String): Vermifugacao?

    @Transaction
    @Query("DELETE FROM Vermifugacao WHERE id = :id")
    suspend fun deleteById(id: String)
}