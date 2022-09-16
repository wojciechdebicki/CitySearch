package com.wojdeb.citysearch.networking

import com.wojdeb.citysearch.networking.domain.toDomain
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val geoNamesService: GeoNamesService) {

    suspend fun fetch(text: String) =
        geoNamesService.listGeonames(text).geonames.map { it.toDomain() }
}