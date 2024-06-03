package com.example.weatherapp.ui

import com.example.weatherapp.data.model.RemoteResult

data class AppUiState (
    // COMENTARIO.
    var remoteResult: RemoteResult? = null,

    // COMENTARIO.
    var city: String = "Jaca",
    var country: String = "",
    var temperature: String = "",
    var weather: String = "",
    var iconID: String = "",

    // COMENTARIO
    var error: String = "",
    var showError: Boolean = false
)