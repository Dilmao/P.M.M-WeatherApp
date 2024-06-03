package com.example.weatherapp.screens.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.AppUiState

@Composable
fun Temperature(appUiState: AppUiState) {
    // COMENTARIO.
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        // COMENTARIO.
        Text(text = appUiState.temperature, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Text(text = "ºC", fontSize = 20.sp, modifier = Modifier.padding(top = 5.dp))

        Spacer(modifier = Modifier.width(10.dp))

        // COMENTARIO.
        Column {
            Spacer(modifier = Modifier.height(15.dp))

            // COMENTARIO.
            Row {
                Text(text = "Mín: ${appUiState.temperature}", fontSize = 10.sp)
                Text(text = "ºC", fontSize = 5.sp, modifier = Modifier.padding(top = 2.dp))
            }

            // COMENTARIO.
            Row {
                Text(text = "Máx: ${appUiState.temperature}", fontSize = 10.sp)
                Text(text = "ºC", fontSize = 5.sp, modifier = Modifier.padding(top = 2.dp))
            }
        }
    }
}