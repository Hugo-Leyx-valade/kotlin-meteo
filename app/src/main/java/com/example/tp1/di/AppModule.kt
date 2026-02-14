package com.example.tp1.di

import com.example.tp1.data.remote.GeoApi
import com.example.tp1.data.remote.WeatherApi
import com.example.tp1.data.repository.GeoRepository
import com.example.tp1.data.repository.WeatherRepository
import com.example.tp1.ui.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    // Retrofit
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // WeatherApi
    single<WeatherApi> {
        get<Retrofit>().create(WeatherApi::class.java)
    }

    // Repository
    single { WeatherRepository(get()) }

    // ViewModel
    viewModel { WeatherViewModel(get()) }


    single<GeoApi>{
        get<Retrofit>().create(GeoApi::class.java)
    }

    // Repository
    single { GeoRepository(get()) }

}

