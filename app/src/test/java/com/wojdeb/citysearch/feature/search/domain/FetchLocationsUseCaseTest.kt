package com.wojdeb.citysearch.feature.search.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wojdeb.citysearch.common.TestCoroutineRule
import com.wojdeb.citysearch.networking.LocationsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
internal class FetchLocationsUseCaseTest {

    private val fetchLocationsRepository = mockk<LocationsRepository>()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun fetching_and_sorting_locations() = runTest {
        val fakedLocations = getFakedLocations()

        coEvery { fetchLocationsRepository.fetch(any()) } returns fakedLocations

        val useCase = FetchLocationsUseCase(fetchLocationsRepository)

        val items = useCase.execute("")


        assertThat(items.size).isEqualTo(10)
        assertThat(items.first().countryName).isEqualTo("United States")
        assertThat(items.first().stateName).isEqualTo("FL")
        assertThat(items[2].countryName).isEqualTo("Australia")

        assertThat(items).isEqualTo(getSortedLocations())
    }

    private fun getFakedLocations(): List<Location> {
        val location1 = Location(cityName = "Sydney", stateName = "02", countryName = "Australia")
        val location2 = Location(cityName = "Sydney", stateName = "07", countryName = "Canada")
        val location3 = Location(cityName = "Sidney", stateName = "OH", countryName = "United States")
        val location4 = Location(cityName = "City of Sydney", stateName = "02", countryName = "Australia")
        val location5 = Location(cityName = "Sydney", stateName = "02", countryName = "Australia")
        val location6 = Location(cityName = "Sydney", stateName = "FL", countryName = "United States")
        val location7 = Location(cityName = "Manra Island", stateName = "03", countryName = "Kiribati")
        val location8 = Location(cityName = "Islotes Las Toscas", stateName = "02", countryName = "Uruguay")
        val location9 = Location(cityName = "Sydney", stateName = "15", countryName = "Vanuatu")
        val location10 = Location(cityName = "Sydney", stateName = "10", countryName = "South Africa")

        return listOf(
            location1,
            location2,
            location3,
            location4,
            location5,
            location6,
            location7,
            location8,
            location9,
            location10
        )
    }

    private fun getSortedLocations(): List<Location> {
        val location1 = Location(cityName = "Sydney", stateName = "FL", countryName = "United States")
        val location2 = Location(cityName = "Sidney", stateName = "OH", countryName = "United States")
        val location3 = Location(cityName = "City of Sydney", stateName = "02", countryName = "Australia")
        val location4 = Location(cityName = "Sydney", stateName = "02", countryName = "Australia")
        val location5 = Location(cityName = "Sydney", stateName = "02", countryName = "Australia")
        val location6 = Location(cityName = "Sydney", stateName = "07", countryName = "Canada")
        val location7 = Location(cityName = "Manra Island", stateName = "03", countryName = "Kiribati")
        val location8 = Location(cityName = "Sydney", stateName = "10", countryName = "South Africa")
        val location9 = Location(cityName = "Islotes Las Toscas", stateName = "02", countryName = "Uruguay")
        val location10 = Location(cityName = "Sydney", stateName = "15", countryName = "Vanuatu")

        return listOf(
            location1,
            location2,
            location3,
            location4,
            location5,
            location6,
            location7,
            location8,
            location9,
            location10
        )
    }

}