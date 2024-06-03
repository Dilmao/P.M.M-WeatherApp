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

    /** COMENTARIO. **/
    @SuppressLint("DefaultLocale")
    fun updateCity(ciudad: String) {
        // Se inicia una nueva corrutina en el scope del ViewModel.
        viewModelScope.launch {
            try {
                // Se crea una instancia de Retrofit para realizar la solicitud de red.
                val service = RetrofitServiceFactory.makeRetrofitService()
                val weatherResult = service.getWeather(API_KEY, ciudad)

                // Se extrae la información de la solicitud.
                val country = weatherResult.sys.country
                val city = weatherResult.name
                val temperature = weatherResult.main.temp.minus(273.15).let { String.format("%.0f", it) }
                val minTemperature = weatherResult.main.temp_min.minus(273.15).let { String.format("%.0f", it) }
                val maxTemperature = weatherResult.main.temp_max.minus(273.15).let { String.format("%.0f", it) }
                val weather = weatherResult.weather.firstOrNull()?.description?.uppercase() ?: ""
                val icon = weatherResult.weather.firstOrNull()?.icon ?: ""

                // Se actualiza el estado de la UI con la nueva información.
                _appUiState.update { currentState ->
                    currentState.copy(
                        city = city,
                        country = country,
                        temperature = temperature,
                        minTemperature = minTemperature,
                        maxTemperature = maxTemperature,
                        weather = weather,
                        iconID = icon,
                        error = "",
                        showError = false
                    )
                }
            } catch (e: Exception) {
                // En caso de error, se actualiza el estado de la UI para reflejar el error.
                _appUiState.update { currentState ->
                    currentState.copy(
                        error = "No se ha podido encontrar la ciudad introducida: \"$ciudad\".",
                        showError = true
                    )
                }
            }
        }
    }
}