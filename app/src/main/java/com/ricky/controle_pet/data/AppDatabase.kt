package com.ricky.controle_pet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ricky.controle_pet.data.converters.Converters
import com.ricky.controle_pet.data.dao.AnimalDao
import com.ricky.controle_pet.data.dao.VacinaDao
import com.ricky.controle_pet.data.dao.VermifugacaoDao
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.Vermifugacao

@Database(
    entities = [
        Vacina::class,
        Vermifugacao::class,
        Animal::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun vacinaDao(): VacinaDao
    abstract fun animalDao(): AnimalDao
    abstract fun vermifugacaoDao(): VermifugacaoDao
}