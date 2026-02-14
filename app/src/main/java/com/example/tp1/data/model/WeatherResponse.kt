package com.example.tp1.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current_weather: CurrentWeather
)

data class CurrentWeather(
    @SerializedName("temperature")
    val temp: Double
)
