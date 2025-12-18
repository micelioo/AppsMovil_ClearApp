package com.example.et_clearapp.network

import com.example.et_clearapp.model.Animo
import retrofit2.http.*

interface AnimoApi {

    @GET("api/animo/listar")
    suspend fun listar(): List<Animo>

    @GET("api/animo/buscar/{id}")
    suspend fun buscar(@Path("id") id: Long): Animo

    @POST("api/animo/guardar")
    suspend fun guardar(@Query("usuarioId") usuarioId: Long, @Body animo: Animo): Animo

    @PUT("api/animo/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Query("usuarioId") usuarioId: Long, @Body animo: Animo): Animo

    @DELETE("api/animo/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}
