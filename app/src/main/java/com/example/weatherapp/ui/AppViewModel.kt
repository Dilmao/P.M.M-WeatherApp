package com.example.weatherapp.ui

import android.annotation.SuppressLint
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

    @SuppressLint("DefaultLocale")
    fun updateCity(ciudad: String) {
        viewModelScope.launch {
            try {
                // Obtener el servicio y los resultados del clima
                val service = RetrofitServiceFactory.makeRetrofitService()
                val weatherResult = service.getWeather(API_KEY, ciudad)

                // Extraer los datos necesarios
                val country = weatherResult.sys.country
                val city = weatherResult.name
                val temperature = weatherResult.main.temp.minus(273.15).let { String.format("%.0f", it) }
                val weather = weatherResult.weather.firstOrNull()?.description?.uppercase() ?: ""
                val icon = weatherResult.weather.firstOrNull()?.icon ?: ""

                // Actualizar el estado de la UI
                _appUiState.update { currentState ->
                    currentState.copy(
                        city = city,
                        country = country,
                        temperature = temperature,
                        weather = weather,
                        iconID = icon,
                        error = "",
                        showError = false
                    )
                }
            } catch (e: Exception) {
                // Manejo de errores
                _appUiState.update { currentState ->
                    currentState.copy(
                        error = "No se ha podido encontrar la ciudad introducida ($ciudad).",
                        showError = true
                    )
                }
            }
        }
    }
}