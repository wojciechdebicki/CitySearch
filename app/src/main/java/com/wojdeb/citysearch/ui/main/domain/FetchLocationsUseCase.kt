package com.wojdeb.citysearch.ui.main.domain

import com.wojdeb.citysearch.networking.GeoNamesRepository
import com.wojdeb.citysearch.networking.domain.toDomain
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(private val geoNamesRepository: GeoNamesRepository) {

    suspend fun execute(text: String): List<Location> {
        val items = geoNamesRepository.fetch(text).map { it.toDomain() }

        val sorted: MutableList<Location> =
            items.sortedWith(compareBy({ it.countryName }, { it.stateName }, { it.cityName }))
                .toMutableList()

        //We should use sorting for it, e.g. custom comparator
        val usElements = sorted.filter { it.countryName == "United States" }

        sorted.removeAll(usElements)
        sorted.addAll(0, usElements)

        return sorted
    }

}