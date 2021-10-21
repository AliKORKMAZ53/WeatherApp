package com.example.weatherapp.data.api

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {

    @GET("api/location/search/?lattlong={latitude},{longtitude}")
    suspend fun getNearLocation(
        @Path("latitude") latitude: String,
        @Path("longtitude") longtitude: String
    ):Response<LocationResponse>

    @GET("api/location/{locationId}")
    suspend fun getWeather(
        @Path("locationId") locationId: Int
    ):Response<WeatherResponse>
}