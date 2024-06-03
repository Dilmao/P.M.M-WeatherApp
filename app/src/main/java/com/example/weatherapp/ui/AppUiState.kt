package com.example.weatherapp.ui

data class AppUiState (
    // Variables para almacenar la información de la ciudad y su estado meteorológico.
    var city: String = "Jaca",
    var country: String = "",
    var temperature: String = "",
    var minTemperature: String = "",
    var maxTemperature: String = "",
    var weather: String = "",
    var iconID: String = "",

    // Variables para manejar errores en la solicitud y mostrar mensajes de error.
    var error: String = "",
    var showError: Boolean = false
)