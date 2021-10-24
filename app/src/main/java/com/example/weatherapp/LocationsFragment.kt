package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.data.util.Resource
import com.example.weatherapp.databinding.FragmentLocationsBinding
import com.example.weatherapp.presentation.adapter.LocationAdapter
import com.example.weatherapp.presentation.viewmodel.LocationViewModel

class LocationsFragment : Fragment() {
    private lateinit var fragmentLocationsBinding: FragmentLocationsBinding
    private lateinit var locationViewModel: LocationViewModel
    private lateinit var locationAdapter: LocationAdapter
    var locationlls:ArrayList<String>?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_locations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLocationsBinding= FragmentLocationsBinding.bind(view)
        locationViewModel=(activity as MainActivity).locationViewModel
        locationAdapter=(activity as MainActivity).locationAdapter
        locationlls=(activity as MainActivity).doubleArray
        locationAdapter.setOnItemClickListener {
            val bundle=Bundle().apply {
                putSerializable("locationbundle",it)
            }
            findNavController().navigate(
                R.id.action_locationsFragment_to_weatherFragment,bundle
            )
        }

        initRecyclerView()
        initLocationList()
    }

    private fun initLocationList() {
        if(locationlls!=null){
            locationViewModel.getLocations(locationlls?.get(0).toString()+","+locationlls?.get(1).toString())
            locationViewModel.locationInfo.observe(viewLifecycleOwner,{response->
             when(response){
                 is Resource.Success->{
                     //hideprogressBar()
                     response.data?.let {
                         locationAdapter.differ.submitList(it)
                     }
                 }
                 is Resource.Error->{
                     //hideprogressbar()
                     Log.d("locationcheck",response.message.toString())
                 }
             }


            }
            )
        }else{
            Toast.makeText(activity,"Error while getting location from main activity",Toast.LENGTH_LONG).show()
        }
    }

    private fun initRecyclerView() {
        fragmentLocationsBinding.locationsRecycView.apply {
            adapter=locationAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }


}