package com.example.et_clearapp.model

import java.time.LocalDate

data class Tarea(
    val id: Long? = null,
    val title: String,
    val dueDate: LocalDate,
    val prioridad: Prioridad,
    val done: Boolean = false
)