package com.example.weatherapp.data.repository.DataSourceImpl

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.repository.DataSource.RemoteDataSource
import retrofit2.Response

class RemoteDataSourceImpl(private val weatherApiService: WeatherApiService)
    :RemoteDataSource{
    override suspend fun getLocation(
        latitude: String,
        longtitude: String
    ): Response<LocationResponse> {
        return weatherApiService.getNearLocation(latitude, longtitude)
    }

    override suspend fun getWeather(locationId: Int): Response<WeatherResponse> {
        return weatherApiService.getWeather(locationId)
    }
}