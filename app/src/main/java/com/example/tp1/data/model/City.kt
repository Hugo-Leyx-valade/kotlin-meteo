package com.example.tp1.data.model

data class City (var name: String, var latitude: Double, var longitude: Double )

data class CitySearchResponse(
    val results: City? = null
)
