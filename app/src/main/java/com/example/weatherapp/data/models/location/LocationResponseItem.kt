package com.example.weatherapp.data.models.location


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LocationResponseItem(
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("latt_long")
    val lattLong: String,
    @SerializedName("location_type")
    val locationType: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("woeid")
    val woeid: Int
):Serializable