package com.example.et_clearapp.network

import com.example.et_clearapp.model.Usuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuariosApi {

    @POST("api/usuarios/login")
    suspend fun login(@Body body: LoginRequest): Usuario

    @POST("api/usuarios/guardar")
    suspend fun registrar(@Body user: Usuario): Usuario

    @GET("api/usuarios/listar")
    suspend fun listar(): List<Usuario>

    @GET("api/usuarios/buscar/{id}")
    suspend fun buscar(@Path("id") id: Long): Usuario
}

data class LoginRequest(
    val email: String,
    val password: String
)
