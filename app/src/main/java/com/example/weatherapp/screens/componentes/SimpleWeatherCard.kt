package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherCard(appViewModel: AppViewModel) {
    // Se recoge el estado actual de la UI desde el ViewModel.
    val appUiState by appViewModel.appUiState.collectAsState()

    // Se crea una tarjeta que contiene la información del clima.
    Card (
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 50.dp),
        modifier = Modifier
            .size(200.dp, 250.dp)
            .padding(10.dp),
    ) {
        Column (
            modifier = Modifier.padding(20.dp)
        ) {
            // Se muestra la ciudad y el país.
            CityAndCountry(appUiState)

            // Se muestra la temperatura actual.
            Temperature(appUiState)
            Spacer(modifier = Modifier.height(10.dp))

            // Se muestra la descripción del clima.
            WeatherDescription(appUiState)
        }
    }
}
