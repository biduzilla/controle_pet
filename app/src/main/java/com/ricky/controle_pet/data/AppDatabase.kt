package com.ricky.controle_pet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ricky.controle_pet.data.converters.Converters
import com.ricky.controle_pet.data.dao.AnimalDao
import com.ricky.controle_pet.data.dao.VacinaDao
import com.ricky.controle_pet.data.dao.VermifugacaoDao
import com.ricky.controle_pet.data.dao.VetDao
import com.ricky.controle_pet.domain.model.Animal
import com.ricky.controle_pet.domain.model.Vacina
import com.ricky.controle_pet.domain.model.Vermifugacao
import com.ricky.controle_pet.domain.model.Vet

@Database(
    entities = [
        Vacina::class,
        Vet::class,
        Vermifugacao::class,
        Animal::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun vacinaDao(): VacinaDao
    abstract fun vetDao(): VetDao
    abstract fun animalDao(): AnimalDao
    abstract fun vermifugacaoDao(): VermifugacaoDao
}