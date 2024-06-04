package com.example.weatherapp.data

import com.example.weatherapp.data.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    /** Se consigue la información del clima a traves de la ciudad **/
    //https://api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
    @GET("data/2.5/weather?&lang=es")
    suspend fun getWeatherCity(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): RemoteResult

    /** Se consigue la información del clima a traves de las coordenadas **/
    //https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}
    @GET("data/2.5/weather?&lang=es")
    suspend fun getWeatherCords(
        @Query("lat") cityLat: String,
        @Query("lon") cityLon: String,
        @Query("appid") apiKey: String
    ): RemoteResult
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}