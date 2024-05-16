package com.example.weatherapp.scaffold

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTitleTopBar(titulo: String) {
    CenterAlignedTopAppBar(
        title = { Text(text = titulo) },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.LightGray,
            titleContentColor = Color.Black,
        )
    )
}