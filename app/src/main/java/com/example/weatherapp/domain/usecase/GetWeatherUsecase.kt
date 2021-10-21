package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherAppRepository

class GetWeatherUsecase(private val weatherAppRepository: WeatherAppRepository) {
    suspend fun execute(locationId:Int): Resource<WeatherResponse>{
        return weatherAppRepository.getSpesificWeather(locationId)
    }
}