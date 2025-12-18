package com.example.et_clearapp.viewmodel

data class EstadoAnimoUiState(
    val moodSeleccionado: String? = null,
    val comentario: String = "",
    val errorMood: String? = null,
    val errorComentario: String? = null
)
