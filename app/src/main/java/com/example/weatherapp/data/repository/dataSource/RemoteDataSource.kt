package com.example.weatherapp.data.repository.dataSource

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun getLocation(lattlong:String):Response<LocationResponse>
    suspend fun getWeather(locationId:Int):Response<WeatherResponse>
}