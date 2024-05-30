package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.screens.WeatherScreen
import com.example.weatherapp.ui.AppViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val appViewModel = AppViewModel()
    NavHost(navController = navController, startDestination = AppScreens.WeatherScreen.route) {
        composable(route = AppScreens.WeatherScreen.route) { WeatherScreen(navController, appViewModel)}
    }
}