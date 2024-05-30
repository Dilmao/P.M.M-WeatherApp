package com.example.weatherapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.RetrofitServiceFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

var API_KEY = "93918e4c01b474b2757b449474dd8021"
class AppViewModel: ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState

    fun updateWeather(city: String) {
        viewModelScope.launch {
            val service = RetrofitServiceFactory.makeRetrofitService()
            val weatherResult = service.getWeather(API_KEY, city)

            _appUiState.update { currentState ->
                currentState.copy(clima = weatherResult)
            }
        }
    }

    fun updateCity(city: String) {
        _appUiState.update {currentState ->
            currentState.copy(ciudad = city)
        }
        updateWeather(city)
    }
}