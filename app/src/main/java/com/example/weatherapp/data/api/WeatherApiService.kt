package com.example.weatherapp.data.api

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApiService {

    @GET("api/location/search")
    suspend fun getNearLocation(
        @Query("lattlong") latitude: String,
        @Query("lattlong") longtitude: String
    ):Response<LocationResponse>

    @GET("api/location/{locationId}")
    suspend fun getWeather(
        @Path("locationId") locationId: Int
    ):Response<WeatherResponse>
}