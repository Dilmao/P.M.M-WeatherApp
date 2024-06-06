package com.example.weatherapp.scaffold

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyStartTopBar(appViewModel: AppViewModel) {
    // COMENTARIO.
    CenterAlignedTopAppBar(
        title = {
            Text(text = "Weather App")
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchTopBar(appViewModel: AppViewModel) {
    // COMENTARIO.
    var city by rememberSaveable { mutableStateOf("") }

    // COMENTARIO.
    CenterAlignedTopAppBar(
        title = {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text(text = "Buscar ciudad", color = Color.Black) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(fontSize = 15.sp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            appViewModel.updateCity(city)
                            city = ""
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Buscar ciudad")
                    }
                },
                modifier = Modifier
                    .width(250.dp)
                    .height(60.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        modifier = Modifier.height(90.dp),
    )
}