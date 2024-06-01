package com.example.weatherapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavHostController
import com.example.weatherapp.scaffold.MyTitleTopBar
import com.example.weatherapp.screens.componentes.WeatherCard
import com.example.weatherapp.ui.AppViewModel

// TODO:
//  1. Mejorar la busqueda de ciudades (WeatherScreen).
//  2. Mejorar el manejo de errores al no encontrar ciudad (AppViewModel).
//  3. Lograr que se muestre el icono del clima (WeatherCard).
//  5. ¿Mejorar el diseño de la app (WeatherScreen)?.

@Composable
fun WeatherScreen(navController: NavHostController, appViewModel: AppViewModel) {
    // TODO cambiar icono de la applicacion
    // Se obtiene el estado de la UI.
    val appUiState by appViewModel.appUiState.collectAsState()

    // Se inicializa la applicación.
    appViewModel.updateCity(appUiState.city)

    // Se configura un Scaffold con una barra superior.
    Scaffold(
        topBar = { MyTitleTopBar(titulo = "El tiempo en ", appUiState.city) }
    ) { paddingValues ->
        // Se configura un contenedor con fondo azul oscruo.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF001F54))  // Azul oscuro.
        ) {
            // Se muestra el contenido de la app.
            WeatherScreenBodyContent(navController, appViewModel)
        }
    }
}

@Composable
fun WeatherScreenBodyContent(navController: NavHostController, appViewModel: AppViewModel) {
    // Se obtiene el estado de la UI.
    val appUiState by appViewModel.appUiState.collectAsState()
    val context = LocalContext.current
    var city by rememberSaveable { mutableStateOf("") }

    // COMENTARIO.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Campo de texto para cambiar la ciudad.
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text(text = "Ciudad", color = Color.White) },
            textStyle = TextStyle(color = Color.White),
        )

        // Tarjeta para mostrar la información del clima.
        WeatherCard(appViewModel = appViewModel)

        Button(onClick = {
            if (!appViewModel.updateCity(city)) {
                Toast.makeText(context, appUiState.error, Toast.LENGTH_SHORT).show()
            }
            city = ""
        }) {
            Text(text = "Cambiar ciudad")
        }
    }
}
