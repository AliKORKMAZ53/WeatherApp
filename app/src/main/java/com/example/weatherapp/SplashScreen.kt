package com.example.weatherapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

class SplashScreen : AppCompatActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }
    lateinit var location: Location
    private var cancellationTokenSource = CancellationTokenSource()
    lateinit var button:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Log.d("Splashcheck","permission granted")
        button = findViewById<Button>(R.id.startAppButton)
        button.setOnClickListener {
            checkPermission()
            button.setText("Loading...")
        }

    }
    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)!=PackageManager.PERMISSION_GRANTED||
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,//shouldshowrequestpermission i da deneyebilirsin
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.INTERNET,Manifest.permission.ACCESS_NETWORK_STATE),40)
            Log.d("Splashcheck","permission granted")

            checkPermission()
        }else{
            requestCurrentLocation()
        }
    }
    @SuppressLint("MissingPermission")
    private fun requestCurrentLocation(){
        var result: Location? =null
        // Check Fine permission

            // Main code
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful) {
                    result= task.result
                    Log.d("Splashcheck",result?.latitude.toString())
                    listenLocation(result)
                } else {
                    result=null
                    val exception = task.exception
                    Log.d("Splashcheck",exception?.message.toString())
                }

            }



    }
    fun listenLocation(location: Location?){
        if(location!=null){
            var i=Intent(this,MainActivity::class.java)
            i.putStringArrayListExtra("locationlls", arrayListOf(location?.latitude.toString(),location?.longitude.toString()))
            startActivity(i)
            finish()
        }else{
            Toast.makeText(applicationContext, "Error while getting location!",Toast.LENGTH_LONG).show()
        }
    }


    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }


}