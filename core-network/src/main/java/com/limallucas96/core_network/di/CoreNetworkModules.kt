package com.limallucas96.core_network.di

import com.google.gson.Gson
import com.limallucas96.core_network.datasources.CatDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModules {

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): CatDataSource {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.thecatapi.com/v1/")
            .client(okHttpClient)
            .build()
            .create(CatDataSource::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

}