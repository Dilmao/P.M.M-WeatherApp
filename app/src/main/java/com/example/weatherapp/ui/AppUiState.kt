package com.example.weatherapp.ui

import com.example.weatherapp.data.model.RemoteResult

data class AppUiState (
    var clima: RemoteResult? = null,
    var ciudad: String = "Jaca",
)