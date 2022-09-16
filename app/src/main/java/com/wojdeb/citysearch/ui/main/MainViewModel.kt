package com.wojdeb.citysearch.ui.main

import androidx.lifecycle.ViewModel
import com.wojdeb.citysearch.networking.GeoNamesService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val geoNamesService: GeoNamesService
) : ViewModel() {

    suspend fun getLocations(text: String) = geoNamesService.listGeonames(text)
}