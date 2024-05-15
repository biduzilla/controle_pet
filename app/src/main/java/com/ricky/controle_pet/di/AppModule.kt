package com.ricky.controle_pet.di

import android.content.Context
import androidx.room.Room
import com.ricky.controle_pet.data.AppDatabase
import com.ricky.controle_pet.data.DataStoreUtil
import com.ricky.controle_pet.data.dao.AnimalDao
import com.ricky.controle_pet.data.dao.VacinaDao
import com.ricky.controle_pet.data.dao.VermifugacaoDao
import com.ricky.controle_pet.data.dao.VetDao
import com.ricky.controle_pet.data.repository.AnimalRepositoryImpl
import com.ricky.controle_pet.data.repository.VacinaRepositoryImpl
import com.ricky.controle_pet.data.repository.VermifugacaoRepositoryImpl
import com.ricky.controle_pet.data.repository.VetRepositoryImpl
import com.ricky.controle_pet.domain.repository.AnimalRepository
import com.ricky.controle_pet.domain.repository.VacinaRepository
import com.ricky.controle_pet.domain.repository.VermifugacaoRepository
import com.ricky.controle_pet.domain.repository.VetRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "app_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil {
        return DataStoreUtil(context)
    }

    @Singleton
    @Provides
    fun provideVacinaDao(database: AppDatabase): VacinaDao = database.vacinaDao()

    @Singleton
    @Provides
    fun provideVetDao(database: AppDatabase): VetDao = database.vetDao()

    @Singleton
    @Provides
    fun provideVermifugacaoDao(database: AppDatabase): VermifugacaoDao = database.vermifugacaoDao()

    @Singleton
    @Provides
    fun provideAnimalDao(database: AppDatabase): AnimalDao = database.animalDao()

    @Singleton
    @Provides
    fun provideVacinaRepository(dao: VacinaDao): VacinaRepository = VacinaRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideVetRepository(dao: VetDao): VetRepository = VetRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideVermifugacaoRepository(dao: VermifugacaoDao): VermifugacaoRepository =
        VermifugacaoRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideAnimalRepository(dao: AnimalDao): AnimalRepository = AnimalRepositoryImpl(dao)
}