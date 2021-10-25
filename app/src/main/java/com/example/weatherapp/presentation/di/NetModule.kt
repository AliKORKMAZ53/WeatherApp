package com.example.weatherapp.presentation.di


import android.util.Log
import com.example.weatherapp.BuildConfig
import com.example.weatherapp.data.api.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsAPIService(retrofit: Retrofit):WeatherApiService{
        return retrofit.create(WeatherApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkhttpClient():OkHttpClient{
        val loggingInterceptorLevel=HttpLoggingInterceptor.Level.BASIC
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=loggingInterceptorLevel
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    }

}