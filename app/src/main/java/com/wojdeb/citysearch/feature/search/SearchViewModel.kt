package com.wojdeb.citysearch.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wojdeb.citysearch.feature.search.domain.FetchLocationsUseCase
import com.wojdeb.citysearch.feature.search.domain.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Loading : State()
    data class Fetched(val items: List<Location>) : State()
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    private val _myUiState = MutableStateFlow<State>(State.Init)
    val myUiState: StateFlow<State> = _myUiState

    fun getLocations(text: String) {
        _myUiState.value = State.Loading

        viewModelScope.launch {
            val items = fetchLocationsUseCase.execute(text)
            _myUiState.value = State.Fetched(items)
        }
    }
}