package com.example.weatherapp.data.repository.dataSourceImpl

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.repository.dataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val weatherApiService: WeatherApiService)
    :RemoteDataSource{
    override suspend fun getLocation(
        lattlong: String
    ): Response<LocationResponse> {
        return weatherApiService.getNearLocation(lattlong)
    }

    override suspend fun getWeather(locationId: Int): Response<WeatherResponse> {
        return weatherApiService.getWeather(locationId)
    }
}