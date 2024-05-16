package com.example.weatherapp.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : AppViewModel() { // TODO muchas cosas que cambiar
    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    init {
        fetchWeather("Jaca")
    }

    private fun fetchWeather(city: String) {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getCurrentWeather(ciry, "93918e4c01b474b2757b449474dd8021")
            _weather.value = response
        }
    }
}