package com.example.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.models.location.LocationResponse
import com.example.weatherapp.data.models.location.LocationResponseItem
import com.example.weatherapp.databinding.LocationListItemBinding

class LocationAdapter:RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {
    private val callback= object : DiffUtil.ItemCallback<LocationResponseItem>(){
        override fun areItemsTheSame(oldItem: LocationResponseItem, newItem: LocationResponseItem): Boolean {
            return oldItem.woeid == newItem.woeid
        }

        override fun areContentsTheSame(oldItem: LocationResponseItem, newItem: LocationResponseItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ= AsyncListDiffer(this,callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding=LocationListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location=differ.currentList[position]
        holder.bind(location)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class LocationViewHolder(
        val binding:LocationListItemBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(locationResponse: LocationResponseItem){
            binding.apply {
                locationTypeTV.text=locationResponse.locationType
                cityNameTV.text=locationResponse.title
                distanceTV.text=locationResponse.distance.toString()
            button.setOnClickListener {
                onItemClickListener?.let {
                    it(locationResponse)
                }
            }
            }


        }
    }

    private var onItemClickListener: ((LocationResponseItem)->Unit)?=null
    fun setOnItemClickListener(listener:(LocationResponseItem)->Unit){
        onItemClickListener=listener
    }


}