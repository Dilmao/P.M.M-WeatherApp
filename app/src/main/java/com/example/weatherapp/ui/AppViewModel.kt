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
    fun updateCity(ciudad: String): Boolean {
        // TODO la excepción de errores no funciona correctamente (no cambia a true).
        var noException = true

        viewModelScope.launch {
            try {
                // COMENTARIO.
                val service = RetrofitServiceFactory.makeRetrofitService()
                val weatherResult = service.getWeather(API_KEY, ciudad)

                // COMENTARIO.
                val country = weatherResult.sys.country
                val city = weatherResult.name
                val temperature = weatherResult.main.temp.minus(273.15).let { String.format("%.0f", it) }
                val weather = weatherResult.weather.firstOrNull()?.description?.uppercase() ?: ""
                val icon = weatherResult.weather.firstOrNull()?.icon ?: ""

                // COMENTARIO.
                _appUiState.update { currentState ->
                    currentState.copy(
                        city = city,
                        country = country,
                        temperature = temperature,
                        weather = weather,
                        iconID = icon
                    )
                }
            } catch (e: Exception) {
                // No funciona el manejo de errores.
                noException = false
            }
        }

        if (noException) {
            _appUiState.update { currentState ->
                currentState.copy(error = "")
            }
        } else {
            _appUiState.update { currentState ->
                currentState.copy(error = "No se ha podido encontrar la ciudad introducida ($ciudad).")
            }
        }

        return noException
    }
}