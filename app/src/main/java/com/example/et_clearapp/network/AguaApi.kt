package com.example.et_clearapp.network

import com.example.et_clearapp.model.Agua
import retrofit2.http.GET

interface AguaApi {
    @GET("api/agua/listar")
    suspend fun listar(): List<Agua>
}
