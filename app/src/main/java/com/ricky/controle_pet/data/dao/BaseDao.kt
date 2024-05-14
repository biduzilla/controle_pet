package com.ricky.controle_pet.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import androidx.room.Update


interface BaseDao<T> {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: T)

    @Transaction
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: T)

    @Transaction
    @Delete
    suspend fun delete(entity: T)

}