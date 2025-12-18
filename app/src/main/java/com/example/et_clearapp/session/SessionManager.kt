package com.example.et_clearapp.session

import androidx.compose.runtime.mutableStateOf
import com.example.et_clearapp.model.Usuario

object SessionManager {
    var usuarioActual: Usuario? = null

    var userId = mutableStateOf<Long?>(null)
    var correoLogeado = mutableStateOf<String?>(null)

    fun setUser(u: Usuario) {
        usuarioActual = u
        userId.value = u.id
        correoLogeado.value = u.email
    }

    fun logout() {
        usuarioActual = null
        userId.value = null
        correoLogeado.value = null
    }
}
