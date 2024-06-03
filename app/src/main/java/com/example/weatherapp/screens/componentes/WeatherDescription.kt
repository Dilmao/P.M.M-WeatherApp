package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherapp.ui.AppUiState

@Composable
fun WeatherDescription(appUiState: AppUiState) {
    // Se construye la URL del icono del clima.
    val iconUrl = "http://openweathermap.org/img/wn/${appUiState.iconID}@2x.png"

    // Se muestra una imagen del icono usando la URL construida.
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(iconUrl)
            .build(),
        contentDescription = "Icono del clima",
        modifier = Modifier.size(70.dp),
        contentScale = ContentScale.Fit
    )
    Spacer(modifier = Modifier.height(10.dp))

    // Se muestra la descripción del clima.
    Text(text = appUiState.weather, fontSize = 12.sp, color = Color(0xFF61656b)) // Gris oscuro.
}
