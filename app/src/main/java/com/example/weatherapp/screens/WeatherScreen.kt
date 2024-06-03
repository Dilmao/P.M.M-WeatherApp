package com.example.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp.scaffold.MySearchTopBar
import com.example.weatherapp.screens.componentes.WeatherCard
import com.example.weatherapp.ui.AppViewModel

// TODO:
//  2. Mejorar el manejo de errores al no encontrar ciudad (AppViewModel).
//  3. Lograr que se muestre el icono del clima (WeatherDescription).

@Composable
fun WeatherScreen(navController: NavHostController, appViewModel: AppViewModel) {
    // Se obtiene el estado de la UI.
    val appUiState by appViewModel.appUiState.collectAsState()

    // Se inicializa la applicación.
    appViewModel.updateCity(appUiState.city)

    // Se configura un Scaffold con una barra superior.
    Scaffold(
        topBar = { MySearchTopBar(appViewModel) }
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

    // COMENTARIO.
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Tarjeta para mostrar la información del clima.
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "EL TIEMPO EN ${appUiState.city.uppercase()}", color = Color.White)
        WeatherCard(appViewModel = appViewModel)

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Pronóstico 3 dias:", color = Color.White)
        WeatherCard(appViewModel = appViewModel)
    }
}
