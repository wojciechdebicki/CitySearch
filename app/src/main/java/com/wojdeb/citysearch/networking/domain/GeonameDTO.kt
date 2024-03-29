package com.wojdeb.citysearch.networking.domain

import com.wojdeb.citysearch.feature.search.domain.Location

data class GeonameDTO(
    var adminCode1: String,
    var lng: String,
    var geonameId: Int,
    var toponymName: String,
    var countryId: String,
    var fcl: String,
    var population: Int,
    var countryCode: String,
    var name: String,
    var fclName: String,
    var countryName: String,
    var fcodeName: String,
    var adminName1: String,
    var lat: String,
    var fcode: String,
)

fun GeonameDTO.toDomain() =
    Location(cityName = toponymName, stateName = adminName1, countryName = countryName)