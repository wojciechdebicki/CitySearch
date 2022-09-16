package com.wojdeb.citysearch.feature.search.domain

import com.wojdeb.citysearch.networking.LocationsRepository
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(private val geoNamesRepository: LocationsRepository) {

    suspend fun execute(text: String): List<Location> {
        val items = geoNamesRepository.fetch(text)

        val sorted: MutableList<Location> =
            items.sortedWith(compareBy({ it.countryName }, { it.stateName }, { it.cityName }))
                .toMutableList()

        //We should use sorting for it, e.g. custom comparator
        val usElements = sorted.filter { it.countryName == US }

        sorted.removeAll(usElements)
        sorted.addAll(0, usElements)

        return sorted
    }

    companion object {
        private const val US = "United States"
    }

}