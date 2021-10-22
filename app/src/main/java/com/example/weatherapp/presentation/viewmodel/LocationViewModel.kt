package com.example.weatherapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherapp.domain.usecase.GetLocationUsecase

class LocationViewModel(
    private val app:Application,
    private val getLocationUsecase: GetLocationUsecase
):AndroidViewModel(app)  {

}