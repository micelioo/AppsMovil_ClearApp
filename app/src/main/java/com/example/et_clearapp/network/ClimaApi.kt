package com.example.et_clearapp.network

import com.example.et_clearapp.data.local.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClimaApi {

    @GET("packages/basic-1h")
    suspend fun getWeather(
        @Query("apikey") apiKey: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("asl") asl: Int,
        @Query("format") format: String = "json"
    ): WeatherResponse
}
