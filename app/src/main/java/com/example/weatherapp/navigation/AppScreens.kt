package com.example.weatherapp.navigation

sealed class AppScreens(val route: String) {
    data object StartScreen: AppScreens(route = "start_screen")
    data object WeatherScreen: AppScreens(route = "weather_screen")
}