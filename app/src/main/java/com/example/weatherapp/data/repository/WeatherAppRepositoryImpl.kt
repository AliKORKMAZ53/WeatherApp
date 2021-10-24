package com.example.weatherapp.data.repository

import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.repository.dataSource.RemoteDataSource
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.repository.WeatherAppRepository
import retrofit2.Response

class WeatherAppRepositoryImpl(
   private val remoteDataSource: RemoteDataSource
):WeatherAppRepository {
    override suspend fun getNearLocations(
        lattlong: String
    ): Resource<LocationResponse> {
        return locationResponseToResource(remoteDataSource.getLocation(lattlong))
    }

    override suspend fun getSpesificWeather(locationId: Int): Resource<WeatherResponse> {
        return weatherResponseToResource(remoteDataSource.getWeather(locationId))
    }
    private fun weatherResponseToResource(response: Response<WeatherResponse>):Resource<WeatherResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
    private fun locationResponseToResource(response: Response<LocationResponse>):Resource<LocationResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}