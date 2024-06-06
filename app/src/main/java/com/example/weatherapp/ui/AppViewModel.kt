package com.example.weatherapp.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.RetrofitServiceFactory
import com.example.weatherapp.data.model.RemoteResult
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

var API_KEY = "93918e4c01b474b2757b449474dd8021"

class AppViewModel: ViewModel() {
    private val _appUiState = MutableStateFlow(AppUiState())
    val appUiState: StateFlow<AppUiState> = _appUiState


    @Composable
    fun GetCords(appViewModel: AppViewModel) {
        val context = LocalContext.current
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        var lat = "" // Jaca: 42.5689800.
        var lon = "" // Jaca: -0.5498700.
        println("lat1: $lat")
        println("lon1: $lon")

        if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        println("lat2: $lat")
        println("lon2: $lon")

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                viewModelScope.launch {
                    try {
                        lat = location.latitude.toString()
                        lon = location.longitude.toString()
                        println("lat3: $lat")
                        println("lon3: $lon")

                        updateCords(lat, lon)
                    } catch (e: Exception) {
                        Toast.makeText(context, ">>> Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Se actualiza la API con los valores pasados.
        appViewModel.updateCords(lat.toString(), lon.toString())
    }

    /** COMENTARIO. **/
    @SuppressLint("DefaultLocale")
    fun updateCords(lat: String, lon: String) {
        // Se inicia una nueva corrutina en el scope del ViewModel.
        viewModelScope.launch {
            try {
                // Se crea una instancia de Retrofit para realizar la solicitud de red.
                val service = RetrofitServiceFactory.makeRetrofitService()
                val weatherResult = service.getWeatherCords(lat, lon, API_KEY)

                // Se actualiza la UI con la información obtenida.
                updateUi(weatherResult)
            } catch (e: Exception) {
                // En caso de error, se actualiza el estado de la UI para reflejar el error.
                _appUiState.update { currentState ->
                    currentState.copy(
                        error = "No se han podido encontrar las coordenadas introducida.",
                        showError = true
                    )
                }
            }
        }
    }

    /** COMENTARIO. **/
    @SuppressLint("DefaultLocale")
    fun updateCity(ciudad: String) {
        // Se inicia una nueva corrutina en el scope del ViewModel.
        viewModelScope.launch {
            try {
                // Se crea una instancia de Retrofit para realizar la solicitud de red.
                val service = RetrofitServiceFactory.makeRetrofitService()
                val weatherResult = service.getWeatherCity(ciudad, API_KEY)

                // Se actualiza la UI con la información obtenida.
                updateUi(weatherResult)
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

    /** COMENTARIO. **/
    @SuppressLint("DefaultLocale")
    fun updateUi(weatherResult: RemoteResult) {
        // Se extrae la información de la solicitud.
        val lat = weatherResult.coord.lat
        val lon = weatherResult.coord.lon
        val city = weatherResult.name
        val country = weatherResult.sys.country
        val temperature = weatherResult.main.temp.minus(273.15).let { String.format("%.0f", it) }
        val minTemperature = weatherResult.main.temp_min.minus(273.15).let { String.format("%.0f", it) }
        val maxTemperature = weatherResult.main.temp_max.minus(273.15).let { String.format("%.0f", it) }
        val weather = weatherResult.weather.firstOrNull()?.description?.uppercase() ?: ""
        val icon = weatherResult.weather.firstOrNull()?.icon ?: ""

        // Se actualiza el estado de la UI con la nueva información.
        _appUiState.update { currentState ->
            currentState.copy(
                lat = lat.toString(),
                lon = lon.toString(),
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
    }
}