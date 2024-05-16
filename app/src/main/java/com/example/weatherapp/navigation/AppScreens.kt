package com.example.weatherapp.navigation

sealed class AppScreens(val route: String) {
    data object WeatherScreen: AppScreens(route = "weather_screen")
}