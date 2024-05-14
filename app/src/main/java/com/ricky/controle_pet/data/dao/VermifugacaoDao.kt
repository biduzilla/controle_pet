package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.ricky.controle_pet.domain.model.Vermifugacao
import kotlinx.coroutines.flow.Flow

@Dao
interface VermifugacaoDao:BaseDao<Vermifugacao> {
    @Query("SELECT * FROM Vermifugacao")
    fun getAllVermifugacao(): Flow<List<Vermifugacao>>

    @Query("SELECT * FROM Vermifugacao WHERE vermifugacaoId = :vermifugacaoId")
    suspend fun getById(vermifugacaoId: String): Vermifugacao?

    @Query("DELETE FROM Vermifugacao WHERE vermifugacaoId = :vermifugacaoId")
    suspend fun deleteById(vermifugacaoId: String)
}