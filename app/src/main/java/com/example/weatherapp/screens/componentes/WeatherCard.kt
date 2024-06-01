package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherCard(appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()

    // COMENTARIO.
    val iconUrl = "http://openweathermap.org/img/wn/${appUiState.iconID}@2x.png"

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
            Row {
                Text(text = appUiState.city)
                Row(
                    modifier = Modifier
                        .align(alignment = Alignment.Top)
                        .padding(start = 4.dp)
                        .background(Color(0xFFFFA500), shape = RoundedCornerShape(10.dp)) // Naranja.
                        .size(20.dp, 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = appUiState.country,
                        fontSize = 7.sp,
                        color = Color.White // Color del texto
                    )
                }
            }

            // Temperatura.
            Row {
                Text(text = appUiState.temperature, fontSize = 40.sp, fontWeight = FontWeight.Bold)
                Text(text = "ºC", fontSize = 20.sp, modifier = Modifier.padding(top = 5.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Icono del clima.
            Image(
                painter = rememberImagePainter(iconUrl),
                contentDescription = "Icono del clima",
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Descripción del clima.
            Text(text = appUiState.weather, fontSize = 12.sp, color = Color(0xFF61656b)) // Gris oscuro.
        }
    }
}
