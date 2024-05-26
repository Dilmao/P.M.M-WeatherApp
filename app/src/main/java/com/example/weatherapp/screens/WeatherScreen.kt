package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.weatherapp.scaffold.MyTitleTopBar
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherScreen(navController: NavHostController, appViewModel: AppViewModel) {
    // COMENTARIO.
    Scaffold (
        topBar = { MyTitleTopBar(titulo = "El tiempo en Jaca") }
    ) {paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            // COMENTARIO.
            WeatherScreenBodyContent(navController, appViewModel)
        }
    }
}

@Composable
fun WeatherScreenBodyContent(navController: NavHostController, appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()
    val context = LocalContext.current

    // COMENTARIO.
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // COMENTARIO.
        Text(text = "TEXTO DE PRUEBA.")
        Text(text = "Temperatura: ${appUiState.clima.main.temp}")
        Text(text = "Temp minima: ${appUiState.clima.main.temp_min}")
        Text(text = "Temp maxima: ${appUiState.clima.main.temp_max}")
        Text(text = "Humedad: ${appUiState.clima.main.humidity}")
        Text(text = "Presion: ${appUiState.clima.main.pressure}")
    }
}
