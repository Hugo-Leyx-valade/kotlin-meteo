package com.example.tp1.data.repository

import android.util.Log
import com.example.tp1.data.model.City
import com.example.tp1.data.model.WeatherResponse
import com.example.tp1.data.remote.WeatherApi

class WeatherRepository(
    private val api: WeatherApi
) {

    private val TAG = "WeatherRepository"

    suspend fun fetchWeather(city: City): WeatherResponse {
        Log.i(TAG,"Fetching weather for ${city.name} (${city.latitude}, ${city.longitude})")
        var weather = api.getWeather(city.longitude, city.latitude)
        Log.i(TAG,"Received weather response: $weather")
        return weather
    }


}
