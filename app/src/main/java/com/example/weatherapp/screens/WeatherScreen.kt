package com.example.weatherapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weatherapp.scaffold.MyTitleTopBar
import com.example.weatherapp.screens.componentes.WeatherCard
import com.example.weatherapp.ui.AppViewModel

@Composable
fun WeatherScreen(navController: NavHostController, appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()

    // COMENTARIO.
    Scaffold (
        topBar = { MyTitleTopBar(titulo = "El tiempo en ", appUiState.ciudad) }
    ) { paddingValues ->
        // COMENTARIO.
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // COMENTARIO.
            appViewModel.updateWeather(appUiState.ciudad)

            // COMENTARIO.
            WeatherScreenBodyContent(navController, appViewModel)
        }
    }
}

@Composable
fun WeatherScreenBodyContent(navController: NavHostController, appViewModel: AppViewModel) {
    // COMENTARIO.
    val appUiState by appViewModel.appUiState.collectAsState()
    var city by rememberSaveable { mutableStateOf(appUiState.ciudad) }

    // COMENTARIO.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // COMENTARIO.
        WeatherCard(appViewModel = appViewModel)

        // TODO pensar una mejor manera de cambiar de ciudad.
        OutlinedTextField(value = city, onValueChange = { city = it })
        Button(onClick = { appViewModel.updateCity(city) }) {
            Text(text = "Cambiar ciudad")
        }
    }
}
