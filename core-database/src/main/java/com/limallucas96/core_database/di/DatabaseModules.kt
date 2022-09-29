package com.limallucas96.core_database.di

import android.content.Context
import androidx.room.Room
import com.limallucas96.core_database.DatabaseConstants.DATA_BASE_NAME
import com.limallucas96.core_database.database.AppDatabase
import com.limallucas96.core_database.datasource.PetDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModules {

    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATA_BASE_NAME
        ).build()


    @Singleton
    @Provides
    fun providesPetDataSource(appDataSource: AppDatabase): PetDataSource {
        return appDataSource.getPetDataSource()
    }
}