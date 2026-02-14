package com.example.tp1.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit_open_meteo = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api_weather: WeatherApi = retrofit_open_meteo.create(WeatherApi::class.java)

    private val retrofit_geolocalisation = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api_geo: GeoApi = retrofit_geolocalisation.create(GeoApi::class.java)

}
