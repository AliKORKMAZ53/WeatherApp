package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.data.models.location.LocationResponseItem
import com.example.weatherapp.data.models.weather.ConsolidatedWeather
import com.example.weatherapp.databinding.WeatherListItemBinding

class WeatherAdapter:RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private val callback= object : DiffUtil.ItemCallback<ConsolidatedWeather>(){
        override fun areItemsTheSame(oldItem: ConsolidatedWeather, newItem: ConsolidatedWeather): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ConsolidatedWeather, newItem: ConsolidatedWeather): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,callback)



    inner class WeatherViewHolder(val binding: WeatherListItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(consolidatedWeather: ConsolidatedWeather){
            binding.apply {
                dayTV.text="${consolidatedWeather.applicableDate}"
                weatherTV.text="${consolidatedWeather.weatherStateName}"
                humidityTV.text="Humidity: ${consolidatedWeather.humidity}"
                temperatureTV.text="Temperature: ${consolidatedWeather.theTemp.toInt()}"
                minTempTV.text="Min. Temp.: ${consolidatedWeather.minTemp.toInt()}"
                maxTempTV.text="Max. Temp.: ${consolidatedWeather.maxTemp.toInt()}"
                windSpeedTV.text="Wind Speed: ${consolidatedWeather.windSpeed.toInt()}"
                windDirectionTV.text="Wind Direction: ${consolidatedWeather.windDirection.toInt()}"
                airPressureTV.text="Air Pressure: ${consolidatedWeather.airPressure.toInt()}"
                visibilityTV.text="Visibility: ${consolidatedWeather.visibility.toInt()}"
                predictibilityTV.text="Predictibility: ${consolidatedWeather.predictability}"

                weatherImg.apply {
                    when(consolidatedWeather.weatherStateAbbr){
                        "c"->{ setImageResource(R.drawable.c)
                        }"h"->{setImageResource(R.drawable.h)
                        }"hc"->{setImageResource(R.drawable.hc)
                        }"hr"->{setImageResource(R.drawable.hr)
                        }"lc"->{setImageResource(R.drawable.lc)
                        }"lr"->{setImageResource(R.drawable.lr)
                        }"s"->{setImageResource(R.drawable.s)
                        }"sl"->{setImageResource(R.drawable.sl)
                        }"sn"->{setImageResource(R.drawable.sn)
                        }"t"->{setImageResource(R.drawable.t)}
                    }
                }


                            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding=WeatherListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather=differ.currentList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}