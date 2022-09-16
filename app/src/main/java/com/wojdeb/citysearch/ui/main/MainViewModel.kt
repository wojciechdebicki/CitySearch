package com.wojdeb.citysearch.ui.main

import androidx.lifecycle.ViewModel
import com.wojdeb.citysearch.ui.main.domain.FetchLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    suspend fun getLocations(text: String) = fetchLocationsUseCase.execute(text)
}