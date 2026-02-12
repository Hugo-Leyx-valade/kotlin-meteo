package com.example.tp1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.tp1.data.model.City
import com.example.tp1.data.repository.WeatherRepository

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _temperature = mutableStateOf<Double?>(null)
    val temperature: State<Double?> = _temperature


    fun loadWeather(city: City) {
        viewModelScope.launch {
            try {
                val response = repository.fetchWeather(city)
                _temperature.value = response.current_weather.temperature
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
