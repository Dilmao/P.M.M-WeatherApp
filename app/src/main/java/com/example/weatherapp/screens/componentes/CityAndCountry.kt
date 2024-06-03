package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.AppUiState

@Composable
fun CityAndCountry(appUiState: AppUiState) {
    // COMENTARIO.
    Row {
        // COMENTARIO.
        Text(text = appUiState.city)

        // COMENTARIO.
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
                color = Color.White
            )
        }
    }
}