package com.example.tp1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.example.tp1.data.model.City
import com.example.tp1.data.repository.GeoRepository
import com.example.tp1.data.repository.WeatherRepository

class GeoViewModel(
    private val repository: GeoRepository
) : ViewModel() {


    private val nullCity = mutableStateOf<City>(City("",0.0,0.0))

    val newCity: State<City> = nullCity


    fun fetchCity(name: String) {
        viewModelScope.launch {
            try {
                val response = repository.fetchCity(name)
                nullCity.value.name = response.name
                nullCity.value.latitude = response.latitude
                nullCity.value.longitude = response.longitude

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
