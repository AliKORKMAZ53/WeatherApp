package com.example.weatherapp.presentation.di

import android.app.Application
import com.example.weatherapp.domain.usecase.GetLocationUsecase
import com.example.weatherapp.domain.usecase.GetWeatherUsecase
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(
        app:Application,
        getLocationUsecase: GetLocationUsecase,
        getWeatherUsecase: GetWeatherUsecase
    ):ViewModelFactory{
        return ViewModelFactory(
            app,
            getLocationUsecase,
            getWeatherUsecase)
    }
}