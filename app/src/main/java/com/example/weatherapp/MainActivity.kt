package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.adapter.LocationAdapter
import com.example.weatherapp.presentation.adapter.WeatherAdapter
import com.example.weatherapp.presentation.viewmodel.LocationViewModel
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var locationAdapter:LocationAdapter
    @Inject
    lateinit var weatherAdapter: WeatherAdapter

    var doubleArray: ArrayList<String>? = null
    private lateinit var binding: ActivityMainBinding
    lateinit var locationViewModel: LocationViewModel
    lateinit var weatherViewModel: WeatherViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        doubleArray=intent.getStringArrayListExtra("locationlls")
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment= supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController= navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        locationViewModel= ViewModelProvider(this,factory)
            .get(LocationViewModel::class.java)
        weatherViewModel= ViewModelProvider(this,factory)
            .get(WeatherViewModel::class.java)


    }
}