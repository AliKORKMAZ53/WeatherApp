package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.util.Resource

interface WeatherAppRepository {
    suspend fun getNearLocations(latitude:String, longtitude:String)
        :Resource<LocationResponse>
    suspend fun getSpesificWeather(locationId:Int)
        :Resource<WeatherResponse>
}