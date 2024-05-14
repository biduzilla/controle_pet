package com.ricky.controle_pet.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ricky.controle_pet.domain.model.Vermifugacao
import com.ricky.controle_pet.domain.model.Vet
import kotlinx.coroutines.flow.Flow

@Dao
interface VermifugacaoDao {
    @Query("SELECT * FROM Vermifugacao")
    fun getAllVermifugacao(): Flow<List<Vermifugacao>>

    @Query("SELECT * FROM Vermifugacao WHERE vermifugacaoId = :vermifugacaoId")
    suspend fun getVermifugacaoById(vermifugacaoId: String): Vet

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVermifugacao(vermifugacao: Vermifugacao)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateVermifugacao(vermifugacao: Vermifugacao)

    @Delete
    suspend fun deleteVermifugacao(vermifugacao: Vermifugacao)

    @Query("DELETE FROM Vermifugacao WHERE vermifugacaoId = :vermifugacaoId")
    suspend fun deleteVermifugacaoById(vermifugacaoId: String)
}