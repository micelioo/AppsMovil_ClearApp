package com.example.et_clearapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.et_clearapp.data.local.MoodDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class EstadoAnimoViewModel(
    private val repo: MoodDataStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        EstadoAnimoUiState()
    )
    val uiState: StateFlow<EstadoAnimoUiState> = _uiState

    init {
        viewModelScope.launch {
            repo.moodSeleccionadoFlow
                .combine(repo.comentarioFlow) { moodGuardado, comentarioGuardado ->
                    EstadoAnimoUiState(
                        moodSeleccionado = moodGuardado,
                        comentario = comentarioGuardado ?: "",
                        errorMood = null,
                        errorComentario = null
                    )
                }
                .collect { loaded ->
                    _uiState.value = loaded
                }
        }
    }

    fun onMoodSelected(nuevoMood: String) {
        _uiState.value = _uiState.value.copy(
            moodSeleccionado = nuevoMood,
            errorMood = null
        )
    }

    fun onComentarioChange(texto: String) {
        _uiState.value = _uiState.value.copy(
            comentario = texto,
            errorComentario = null
        )
    }

    fun guardarEntrada() {
        val current = _uiState.value

        if (current.moodSeleccionado == null) {
            _uiState.value = current.copy(
                errorMood = "Selecciona una emoción"
            )
            return
        }

        if (current.comentario.isBlank()) {
            _uiState.value = current.copy(
                errorComentario = "Escribe algo (aunque sea cortito)"
            )
            return
        }

        viewModelScope.launch {
            repo.guardarMood(current.moodSeleccionado)
            repo.guardarComentario(current.comentario)
        }
    }
}
