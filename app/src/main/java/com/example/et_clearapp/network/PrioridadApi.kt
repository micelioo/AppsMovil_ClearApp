package com.example.et_clearapp.network

import retrofit2.http.GET

interface PrioridadApi {
    @GET("api/prioridad/listar")
    suspend fun listar(): List<String>
}
