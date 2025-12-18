package com.example.et_clearapp.model

import java.time.LocalDate

data class Animo(
    val idRegistroAnimo: Long,
    val fecha: LocalDate,
    val nota: String?,
    val estadoAnimoNombre: String
)

