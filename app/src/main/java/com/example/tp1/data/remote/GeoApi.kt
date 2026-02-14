package com.example.tp1.data.remote

import com.example.tp1.data.model.City
import com.example.tp1.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GeoApi {

    @GET("v1/search")
    suspend fun getCity(
        @Query("name") latitude: String,
    ): City

}
