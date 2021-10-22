package com.example.weatherapp.presentation.di

import com.example.weatherapp.domain.repository.WeatherAppRepository
import com.example.weatherapp.domain.usecase.GetLocationUsecase
import com.example.weatherapp.domain.usecase.GetWeatherUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetLocationUsecase(weatherAppRepository:WeatherAppRepository)
    :GetLocationUsecase{
        return GetLocationUsecase(weatherAppRepository)
    }

    @Singleton
    @Provides
    fun provideGetWeatherUsecase(weatherAppRepository: WeatherAppRepository)
    :GetWeatherUsecase{
        return GetWeatherUsecase(weatherAppRepository)
    }
}