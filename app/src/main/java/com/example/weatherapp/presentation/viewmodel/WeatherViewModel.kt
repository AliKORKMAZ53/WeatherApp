package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.models.weather.WeatherResponse
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.domain.usecase.GetWeatherUsecase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class WeatherViewModel(
    private val app:Application,
    private val getWeatherUsecase: GetWeatherUsecase
):AndroidViewModel(app) {
    val weatherInfo: MutableLiveData<Resource<WeatherResponse>> =MutableLiveData()

    fun getWeather(locationId:Int)=viewModelScope.launch(IO) {
        weatherInfo.postValue(Resource.Loading())
        try{
            if(isNetworkAvailable(app)){
                val response= getWeatherUsecase.execute(locationId)
                weatherInfo.postValue(response)
            }else{
                weatherInfo.postValue(Resource.Error("No internet connection!"))
            }
        }catch (e:Exception){
            weatherInfo.postValue(Resource.Error(e.localizedMessage.toString()))
        }
    }



    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.isDefaultNetworkActive
            if (activeNetworkInfo) {
                return true
            }
        }
        return false
    }
}