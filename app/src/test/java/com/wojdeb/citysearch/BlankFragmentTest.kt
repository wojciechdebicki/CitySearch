package com.wojdeb.citysearch

import android.os.Build
import android.widget.TextView
import com.wojdeb.citysearch.common.launchFragmentInHiltContainer
import com.wojdeb.citysearch.feature.search.SearchViewModel
import com.wojdeb.citysearch.feature.search.State
import com.wojdeb.citysearch.feature.search.domain.Location
import com.wojdeb.citysearch.feature.search.ui.SearchFragment
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(
    sdk = [Build.VERSION_CODES.P], application = HiltTestApplication::class
)
class BlankFragmentTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @BindValue
    val mockMyViewModel = mockk<SearchViewModel>()

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testMainActivity() {

        val location = Location("1", "2", "3")
        val location2 = Location("1", "2", "3")

        val stateMutableStateFlow =
            MutableStateFlow<State>(State.Fetched(listOf(location, location2)))
        every { mockMyViewModel.myUiState } returns stateMutableStateFlow.asStateFlow()
        every { mockMyViewModel.getLocations(any()) } just runs

        launchFragmentInHiltContainer<SearchFragment> {
            this as SearchFragment


            binding.searchInput.setText("san franci", TextView.BufferType.EDITABLE)
            binding.search.performClick()


            assertThat(binding.locations.adapter!!.itemCount).isEqualTo(2)
        }
    }
}