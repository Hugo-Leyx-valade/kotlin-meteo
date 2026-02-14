package com.example.tp1.data.repository

import android.util.Log
import com.example.tp1.data.model.City
import com.example.tp1.data.model.WeatherResponse
import com.example.tp1.data.remote.RetrofitInstance.api_weather
import com.example.tp1.data.remote.WeatherApi

class WeatherRepository(
    private val api: WeatherApi
) {

    private val TAG = "WeatherRepository"

    suspend fun fetchWeather(city: City): WeatherResponse {
        Log.i(TAG,"Fetching weather through : https://api.open-meteo.com/v1/forecast?latitude=${city.latitude}&longitude=${city.longitude}&current_weather=true")
            var weather: WeatherResponse = api_weather.getWeather(city.latitude, city.longitude)
        Log.i(TAG,"Received weather response: $weather")
        return weather
    }


}
