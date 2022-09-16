package com.wojdeb.citysearch.ui.main.domain

import com.wojdeb.citysearch.networking.GeoNamesRepository
import com.wojdeb.citysearch.networking.domain.toDomain
import javax.inject.Inject

class FetchLocationsUseCase @Inject constructor(private val geoNamesRepository: GeoNamesRepository) {
    suspend fun execute(text: String) = geoNamesRepository.fetch(text).map { it.toDomain() }
}