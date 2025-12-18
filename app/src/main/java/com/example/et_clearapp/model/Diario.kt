package com.example.et_clearapp.model

import java.time.LocalDateTime

data class Diario(
    val id: Long? = null,
    val timestamp: LocalDateTime? = null,
    val texto: String,
    val fotoUri: String? = null,
    val moodTag: String? = null,
    val moodComentario: String? = null
)
