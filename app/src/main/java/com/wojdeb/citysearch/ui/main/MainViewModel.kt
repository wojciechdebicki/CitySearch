package com.wojdeb.citysearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wojdeb.citysearch.ui.main.domain.FetchLocationsUseCase
import com.wojdeb.citysearch.ui.main.domain.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class State {
    object Init : State()
    object Loading : State()
    data class Fetched(val items: List<Location>) : State()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchLocationsUseCase: FetchLocationsUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<State>(State.Init)
    val viewState: LiveData<State> = _viewState

    fun getLocations(text: String) {
        _viewState.postValue(State.Loading)

        viewModelScope.launch {
            val items = fetchLocationsUseCase.execute(text)
            _viewState.postValue(State.Fetched(items))
        }
    }
}