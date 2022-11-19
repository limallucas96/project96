package com.example.analytics.di

import com.example.analytics.analytics.Analytics
import com.example.analytics.analytics.AnalyticsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AnalyticsModule {

    @Singleton
    @Binds
    fun bindsAnalytics(analytics: AnalyticsImpl): Analytics

}