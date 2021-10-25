package com.example.weatherapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.presentation.adapter.WeatherAdapter
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel

class WeatherFragment : Fragment() {
    private lateinit var fragmentWeatherBinding: FragmentWeatherBinding
    private lateinit var viewmodel: WeatherViewModel
    private lateinit var weatherAdapter: WeatherAdapter
    private var locationId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: WeatherFragmentArgs by navArgs()
        locationId = args.locationbundle
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentWeatherBinding= FragmentWeatherBinding.bind(view)
        viewmodel=(activity as MainActivity).weatherViewModel
        weatherAdapter=(activity as MainActivity).weatherAdapter

        initRecyclerView()
        viewWeatherList()

    }

    private fun viewWeatherList() {
        if(locationId!=0){
            viewmodel.getWeather(locationId)
            viewmodel.weatherInfo.observe(viewLifecycleOwner,{response->
                when(response){
                    is Resource.Success->{
                        hideProgressBar()
                        response.data?.let {
                            weatherAdapter.differ.submitList(it.consolidatedWeather)
                            fragmentWeatherBinding.cityOnTopTV.text=it.title
                            fragmentWeatherBinding.timeZoneTV.text="Timezone: ${it.timezone}"
                        }
                    }
                    is Resource.Error->{
                        hideProgressBar()
                            Toast.makeText(activity,response.message,Toast.LENGTH_LONG).show()

                    }
                    is Resource.Loading->{
                        showProgressBar()
                    }
                }
            })
        }else{
            Toast.makeText(activity,"Error while getting location",Toast.LENGTH_LONG).show()
        }
    }

    private fun showProgressBar(){
        fragmentWeatherBinding.progressBar.visibility=View.VISIBLE
    }
    private fun hideProgressBar() {
        fragmentWeatherBinding.progressBar.visibility=View.GONE
    }

    private fun initRecyclerView() {
        fragmentWeatherBinding.weatherRV.apply {
            adapter=weatherAdapter
            layoutManager=LinearLayoutManager(activity,LinearLayout.HORIZONTAL,false)
        }
    }

}