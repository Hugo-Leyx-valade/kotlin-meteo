package com.example.tp1.data.repository

import android.util.Log
import com.example.tp1.data.model.City
import com.example.tp1.data.remote.GeoApi
import com.example.tp1.data.remote.RetrofitInstance.api_geo

class GeoRepository(
    private val api: GeoApi
) {

    private val TAG = "WeatherRepository"

    suspend fun fetchCity(input: String): City {
        var city: City = api_geo.getCity(input)
        Log.i(TAG,"Received weather response: $city")
        return city
    }


}
