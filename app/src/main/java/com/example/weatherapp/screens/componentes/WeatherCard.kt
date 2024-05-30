package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherCard(appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()

    // COMENTARIO.
    val temp = appUiState.clima?.main?.temp?.minus(273.15)?.let { String.format("%.0f", it) }
    val tempMin = appUiState.clima?.main?.temp_min?.minus(273.15)?.let { String.format("%.0f", it) }
    val tempMax = appUiState.clima?.main?.temp_max?.minus(273.15)?.let { String.format("%.0f", it) }

    // COMENTARIO.
    Card (
        colors = CardDefaults.cardColors(Color(0xFF58b5bf)),    // Cambiar color, 0xFF es la opacidad, el resto el color en hexadecimal.
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        // COMENTARIO.
        Row (
            modifier = Modifier.fillMaxWidth()
        ) {
            // COMENTARIO.
            Column (
                modifier = Modifier.padding(10.dp)
            ) {
                // COMENTARIO.
                Text(text = "${temp}ºC", fontSize = 30.sp)

                // COMENTARIO.
                Row {
                    Text(text = "Máx: ${tempMax}ºC")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Mín: ${tempMin}ºC")
                }
            }

            // COMENTARIO.
            Column (
                modifier = Modifier.padding(10.dp)
            ) {
                // COMENTARIO.
                Row {
                    Text(text = "Clima: ${appUiState.clima?.weather?.get(0)?.main}")
                }
            }
        }

        // COMENTARIO.
        Spacer(modifier = Modifier.height(50.dp))
    }
}
