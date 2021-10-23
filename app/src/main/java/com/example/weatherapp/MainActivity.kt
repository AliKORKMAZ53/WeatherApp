package com.example.weatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.adapter.LocationAdapter
import com.example.weatherapp.presentation.viewmodel.LocationViewModel
import com.example.weatherapp.presentation.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: ViewModelFactory
    @Inject
    lateinit var locationAdapter:LocationAdapter
    var doubleArray: DoubleArray? = null
    private lateinit var binding: ActivityMainBinding
    lateinit var locationViewModel: LocationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment= supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController= navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        locationViewModel= ViewModelProvider(this,factory)
            .get(LocationViewModel::class.java)
        doubleArray=intent.getDoubleArrayExtra("locationlls")

    }
}