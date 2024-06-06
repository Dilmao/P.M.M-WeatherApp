package com.example.weatherapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.weatherapp.scaffold.MySearchTopBar
import com.example.weatherapp.screens.componentes.WeatherCard
import com.example.weatherapp.ui.AppViewModel

// TODO:
//  1. Lograr que se muestre el icono del clima (WeatherDescription).
//  2. Hacer que la primella llamada a la API use la localización del usuario (WeatherScreen).

@Composable
fun WeatherScreen(navController: NavHostController, appViewModel: AppViewModel) {
    appViewModel.GetCords(appViewModel)

    // Scaffold.
    Scaffold(
        topBar = { MySearchTopBar(appViewModel) }
    ) { paddingValues ->
        // Se crea un contenedor que ocupa el tamaño disponible
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF001F54))  // Azul oscuro.
        ) {
            // Se muestra el contenido principal de la pantalla del clima.
            WeatherScreenBodyContent(navController, appViewModel)
        }
    }
}

@Composable
fun WeatherScreenBodyContent(navController: NavHostController, appViewModel: AppViewModel) {
    // Variables.
    val appUiState by appViewModel.appUiState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Se muestra el clima actual en la ciudad seleccionada.
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "EL TIEMPO EN ${appUiState.city.uppercase()}", color = Color.White)
        WeatherCard(appViewModel = appViewModel)

        // Se muestra el pronostico para los siguientes tres dias en la ciudad seleccionada.
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Pronóstico 3 dias", color = Color.White)
        Text(text = "(funcionalidad no implementada)", fontSize = 7.sp, color = Color.White)
        WeatherCard(appViewModel = appViewModel)
    }

    // En caso de error, se mostrara el mensaje pertinente.
    if (appUiState.showError) {
        Toast.makeText(context, appUiState.error, Toast.LENGTH_SHORT).show()
    }
}