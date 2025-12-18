package com.example.et_clearapp.network

import com.example.et_clearapp.model.Tarea
import retrofit2.Response
import retrofit2.http.*

interface TareasApi {

    @GET("api/tareas/listar")
    suspend fun listar(): List<Tarea>

    @GET("api/tareas/buscar/{id}")
    suspend fun buscar(@Path("id") id: Long): Tarea

    @POST("api/tareas/guardar")
    suspend fun guardar(
        @Query("usuarioId") usuarioId: Long,
        @Body tarea: Tarea
    ): Response<Tarea>

    @PUT("api/tareas/actualizar/{id}")
    suspend fun actualizar(
        @Path("id") id: Long,
        @Query("usuarioId") usuarioId: Long,
        @Body tarea: Tarea
    ): Response<Tarea>

    @DELETE("api/tareas/eliminar/{id}")
    suspend fun eliminar(@Path("id") id: Long)
}
