package com.example.weatherapp.presentation.di

import com.example.weatherapp.data.repository.dataSource.RemoteDataSource
import com.example.weatherapp.data.repository.WeatherAppRepositoryImpl
import com.example.weatherapp.domain.repository.WeatherAppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideWeatherAppRepository(remoteDataSource: RemoteDataSource)
    :WeatherAppRepository{
        return WeatherAppRepositoryImpl(remoteDataSource)
    }
}