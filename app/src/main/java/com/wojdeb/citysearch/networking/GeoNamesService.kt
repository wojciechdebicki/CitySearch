package com.wojdeb.citysearch.networking

import com.wojdeb.citysearch.networking.domain.SearchDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoNamesService {

    @GET("searchJSON")
    suspend fun listGeonames(
        @Query("name_startsWith") text: String,
        @Query("maxRows") maxRows: Int = 10,
        @Query("username") username: String = "keep_truckin"
    ): SearchDTO
}