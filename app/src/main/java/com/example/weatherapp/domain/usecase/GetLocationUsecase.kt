package com.example.weatherapp.domain.usecase

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherAppRepository

class GetLocationUsecase(private val weatherAppRepository: WeatherAppRepository) {
        suspend fun execute(langtitude:String, longtitude:String):Resource<LocationResponse>{
            return weatherAppRepository.getNearLocations(langtitude, longtitude)
        }
}
