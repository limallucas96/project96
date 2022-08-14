package com.limallucas96.core_sharedpreferences.di

import android.content.Context
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferences
import com.limallucas96.core_sharedpreferences.sharedpreferences.AppSharedPreferencesRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreSharedPreferencesModules {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context) : AppSharedPreferences {
        return AppSharedPreferencesRepositoryImp(context)
    }
}