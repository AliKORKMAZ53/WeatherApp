package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.domain.usecase.GetLocationUsecase
import com.example.weatherapp.domain.usecase.GetWeatherUsecase

class ViewModelFactory (
    private val app:Application,
    private val getLocationUsecase: GetLocationUsecase,
    private val getWeatherUsecase: GetWeatherUsecase
        ):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(LocationViewModel::class.java)){
            LocationViewModel(app,getLocationUsecase) as T
        }else if (modelClass.isAssignableFrom(WeatherViewModel::class.java)){
            WeatherViewModel(app,getWeatherUsecase) as T
        }else{
            modelClass as T
        }
    }
}