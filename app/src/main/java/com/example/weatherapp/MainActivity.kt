package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.weatherapp.data.RetrofitServiceFactory
import com.example.weatherapp.data.model.RemoteResult
import com.example.weatherapp.ui.theme.WeatherAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // COMENTAR appViewModel PARA CODIGO QUE NO FALLA.
//        val appViewModel = AppViewModel()
        val service = RetrofitServiceFactory.makeRetrofitService()
        var clima: RemoteResult? = null

//        lifecycleScope.launch {
//            appViewModel.appUiState.value.clima = service.getWeather("93918e4c01b474b2757b449474dd8021", "jaca")
//            println("TEMPERATURA: ${appViewModel.appUiState.value.clima.main.temp}")
//        }
        // CODIGO QUE NO FALLA:
        lifecycleScope.launch {
            clima = service.getWeather("93918e4c01b474b2757b449474dd8021", "jaca")
            println("TEMPERATURA: ${clima!!.main.temp} grados kelvin")
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                // COMENTAR AppNavigation PARA CODIGO QUE NO FALLA.
//                AppNavigation(appViewModel)
            }
        }
    }
}