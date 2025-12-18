package com.example.et_clearapp.model

import java.time.LocalDate

data class Agua(
    val idRegistroAgua: Long,
    val fecha: LocalDate,
    val cantidadMl: Int
)
