package com.example.weatherapp

import android.Manifest
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
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task

class SplashScreen : AppCompatActivity() {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    private var cancellationTokenSource = CancellationTokenSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    var button = findViewById<Button>(R.id.startAppButton)
        button.setOnClickListener {
            checkPermission()
        }

    }
    private fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,//shouldshowrequestpermission i da deneyebilirsin
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),40)
            checkPermission()
        }else{
            var location= requestCurrentLocation()
            if(location!=null){
                var i=Intent(this,MainActivity::class.java)
                i.putExtra("locationlls", arrayOf(location?.latitude,location?.longitude))
                startActivity(i)
            }else{
                Toast.makeText(applicationContext, "Error while getting location!",Toast.LENGTH_LONG).show()

            }

        }
    }
    private fun requestCurrentLocation(): Location? {
        var result: Location? =null
        // Check Fine permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED) {

            // Main code
            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                if (task.isSuccessful) {
                    result= task.result
                } else {
                    result=null
                    val exception = task.exception
                    Toast.makeText(applicationContext, exception?.localizedMessage.toString(),Toast.LENGTH_LONG).show()
                }

            }
        } else {
            Toast.makeText(applicationContext,"Somehow Permission not granted",Toast.LENGTH_LONG).show()

        }
        return result
    }


    override fun onStop() {
        super.onStop()
        cancellationTokenSource.cancel()
    }
}