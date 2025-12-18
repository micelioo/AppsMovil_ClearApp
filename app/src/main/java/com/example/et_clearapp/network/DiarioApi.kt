package com.example.et_clearapp.network

import com.example.et_clearapp.model.Diario
import retrofit2.http.*

interface DiarioApi {

    @GET("api/diario/listar")
    suspend fun listar(): List<Diario>

    @GET("api/diario/buscar/{id}")
    suspend fun buscar(@Path("id") id: Long): Diario

    @POST("api/diario/guardar")
    suspend fun guardar(@Query("usuarioId") usuarioId: Long, @Body diario: Diario): Diario

    @PUT("api/diario/actualizar/{id}")
    suspend fun actualizar(@Path("id") id: Long, @Query("usuarioId") usuarioId: Long, @Body diario: Diario): Diario

    @DELETE("api/diario/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}
