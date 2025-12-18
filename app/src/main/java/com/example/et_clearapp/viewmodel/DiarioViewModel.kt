package com.example.et_clearapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.et_clearapp.model.Diario
import java.time.LocalDateTime

class DiarioViewModel : ViewModel() {

    private var nextId = 1L

    var entradas by mutableStateOf(listOf<Diario>())
        private set

    fun diario(
        texto: String,
        fotoUri: String?
    ) {
        val nueva = Diario(
            id = nextId++,
            timestamp = LocalDateTime.now(),
            texto = texto,
            fotoUri = fotoUri,
            moodTag = null,
            moodComentario = null
        )
        entradas = listOf(nueva) + entradas
    }

    fun animo(
        moodTag: String,
        moodComentario: String,
        fotoUri: String?
    ) {
        val nueva = Diario(
            id = nextId++,
            timestamp = LocalDateTime.now(),
            texto = "",
            fotoUri = fotoUri,
            moodTag = moodTag,
            moodComentario = moodComentario
        )
        entradas = listOf(nueva) + entradas
    }
}
