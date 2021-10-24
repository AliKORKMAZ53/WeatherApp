package com.example.weatherapp.presentation.di

import com.example.weatherapp.presentation.adapter.LocationAdapter
import com.example.weatherapp.presentation.adapter.WeatherAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideLocationAdapter():LocationAdapter{
        return LocationAdapter()
    }
    @Singleton
    @Provides
    fun provideWeatherAdapter():WeatherAdapter{
        return WeatherAdapter()
    }
}