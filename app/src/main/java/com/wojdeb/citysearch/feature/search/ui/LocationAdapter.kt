package com.wojdeb.citysearch.feature.search.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wojdeb.citysearch.common.layoutInflater
import com.wojdeb.citysearch.databinding.LocationRowBinding
import com.wojdeb.citysearch.feature.search.ui.LocationAdapter.LocationViewHolder
import com.wojdeb.citysearch.feature.search.domain.Location

object LocationDiffUtils : DiffUtil.ItemCallback<Location>() {
    override fun areItemsTheSame(oldLocation: Location, newLocation: Location) =
        oldLocation == newLocation

    override fun areContentsTheSame(oldLocation: Location, newLocation: Location) =
        oldLocation == newLocation
}

class LocationAdapter : ListAdapter<Location, LocationViewHolder>(LocationDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding = LocationRowBinding.inflate(parent.layoutInflater(), parent, false)
        return LocationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(private val binding: LocationRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(location: Location) {
            binding.city.text = location.cityName
            binding.country.text = location.countryName
            binding.state.text = location.stateName
        }
    }
}