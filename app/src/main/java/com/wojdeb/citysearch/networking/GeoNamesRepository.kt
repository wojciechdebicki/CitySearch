package com.wojdeb.citysearch.networking

import javax.inject.Inject

class GeoNamesRepository @Inject constructor(private val geoNamesService: GeoNamesService) {

    suspend fun fetch(text: String) = geoNamesService.listGeonames(text).geonames
}