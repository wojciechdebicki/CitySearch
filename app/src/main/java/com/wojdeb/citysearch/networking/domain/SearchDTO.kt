package com.wojdeb.citysearch.networking.domain

data class SearchDTO(val totalResultsCount: Int, val geonames: List<GeoName> )