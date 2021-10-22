package com.example.weatherapp.presentation.di

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.repository.dataSource.RemoteDataSource
import com.example.weatherapp.data.repository.dataSourceImpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideRemoteDataSource(weatherApiService: WeatherApiService)
            :RemoteDataSource{
        return RemoteDataSourceImpl(weatherApiService)
    }
}