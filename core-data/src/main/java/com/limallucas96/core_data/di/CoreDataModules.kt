package com.limallucas96.core_data.di

import android.content.Context
import com.google.gson.Gson
import com.limallucas96.core_data.datasource.CatDataSource
import com.limallucas96.core_data.datasource.SharedPreferenceDataSource
import com.limallucas96.core_data.repositories.sharedpreferences.SharedPreferencesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import org.intellij.lang.annotations.PrintFormat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDataModules {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): CatDataSource {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(okHttpClient)
            .build()
            .create(CatDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun bindSharedPreferencesRepository(@ApplicationContext context: Context): SharedPreferenceDataSource {
        return SharedPreferencesRepositoryImp(context)
    }

}