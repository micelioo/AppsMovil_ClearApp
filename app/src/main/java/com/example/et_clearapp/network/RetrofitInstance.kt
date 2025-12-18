package com.example.et_clearapp.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.time.LocalDateTime

object RetrofitInstance {

    private const val URL_BACKEND = "http://10.0.2.2:9090/"
    private const val URL_CLIMA = "https://my.meteoblue.com/"

    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()

    private val retrofitBackend: Retrofit = Retrofit.Builder()
        .baseUrl(URL_BACKEND)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private val retrofitClima: Retrofit = Retrofit.Builder()
        .baseUrl(URL_CLIMA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val usuariosApi: UsuariosApi = retrofitBackend.create(UsuariosApi::class.java)
    val tareasApi: TareasApi = retrofitBackend.create(TareasApi::class.java)
    val aguaApi: AguaApi = retrofitBackend.create(AguaApi::class.java)
    val animoApi: AnimoApi = retrofitBackend.create(AnimoApi::class.java)
    val diarioApi: DiarioApi = retrofitBackend.create(DiarioApi::class.java)
    val prioridadApi: PrioridadApi = retrofitBackend.create(PrioridadApi::class.java)

    val climaApi: ClimaApi = retrofitClima.create(ClimaApi::class.java)
}
