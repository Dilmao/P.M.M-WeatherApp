package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherCard(appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()

    // COMENTARIO.
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
            // Ciudad y pais.
            CityAndCountry(appUiState)

            // Temperatura.
            Temperature(appUiState)
            Spacer(modifier = Modifier.height(10.dp))

            // Icono y descripción del clima.
            WeatherDescription(appUiState)
        }
    }
}
