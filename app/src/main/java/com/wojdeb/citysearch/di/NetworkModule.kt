package com.wojdeb.citysearch.di

import com.wojdeb.citysearch.networking.GeoNamesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().baseUrl("https://secure.geonames.org/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    @Provides
    fun provideGeoNamesService(retrofit: Retrofit): GeoNamesService =
        retrofit.create(GeoNamesService::class.java)
}