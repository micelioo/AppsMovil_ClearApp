package com.example.et_clearapp.data.local

data class WeatherResponse(
    val data_1h: Data1h?
)

data class Data1h(
    val temperature: List<Double>?
)
