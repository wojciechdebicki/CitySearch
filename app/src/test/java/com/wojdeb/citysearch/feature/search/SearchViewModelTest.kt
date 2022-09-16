package com.wojdeb.citysearch.feature.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.wojdeb.citysearch.common.TestCoroutineRule
import com.wojdeb.citysearch.common.captureValues
import com.wojdeb.citysearch.feature.search.domain.FetchLocationsUseCase
import com.wojdeb.citysearch.feature.search.domain.Location
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
internal class SearchViewModelTest {
    private val fetchLocationsUseCase = mockk<FetchLocationsUseCase>()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Test
    fun verify_get_locations_flow() = runTest {
        val fakedLocations = getFakedLocations()

        coEvery { fetchLocationsUseCase.execute(any()) } returns fakedLocations

        val viewModel = SearchViewModel(fetchLocationsUseCase)

        viewModel.viewState.captureValues {
            viewModel.getLocations("")

            assertThat(values[0]).isInstanceOf(State.Init::class.java)
            assertThat(values[1]).isInstanceOf(State.Loading::class.java)
            assertThat(values[2]).isInstanceOf(State.Fetched::class.java)

            val theFetched = values[2] as State.Fetched

            assertThat(theFetched.items.size).isEqualTo(fakedLocations.size)
        }
    }

    private fun getFakedLocations(): List<Location> {
        val location1 = Location(cityName = "c1", stateName = "l1", countryName = "l1")
        val location2 = Location(cityName = "c2", stateName = "s2", countryName = "n2")
        val location3 = Location(cityName = "c3", stateName = "s3", countryName = "n3")
        return listOf(location1, location2, location3)
    }
}