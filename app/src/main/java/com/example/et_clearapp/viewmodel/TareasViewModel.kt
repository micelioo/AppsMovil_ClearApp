package com.example.et_clearapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.et_clearapp.model.Prioridad
import com.example.et_clearapp.model.Tarea
import com.example.et_clearapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.time.LocalDate

class TareasViewModel : ViewModel() {

    var tareas = mutableStateListOf<Tarea>()
        private set

    val pendientesCount: Int
        get() = tareas.count { !it.done }

    val completadasCount: Int
        get() = tareas.count { it.done }

    var mostrarDialogo = mutableStateOf(false)
        private set

    fun abrirDialogo() {
        mostrarDialogo.value = true
    }

    fun cerrarDialogo() {
        mostrarDialogo.value = false
    }

    fun guardarTarea(
        titulo: String,
        fecha: LocalDate,
        prioridad: Prioridad,
        usuarioId: Long
    ) {
        val nuevaTarea = Tarea(
            title = titulo,
            dueDate = fecha,
            prioridad = prioridad,
            done = false
        )

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.tareasApi.guardar(
                    usuarioId = usuarioId,
                    tarea = nuevaTarea
                )

                if (response.isSuccessful) {
                    response.body()?.let {
                        tareas.add(it)
                        Log.d("TAREA", "Guardada OK")
                    }
                } else {
                    Log.e("TAREA", "Error backend ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("TAREA", "Excepción", e)
            }
        }
    }

    fun toggleTarea(tarea: Tarea) {
        val index = tareas.indexOfFirst { it.id == tarea.id }
        if (index != -1) {
            tareas[index] = tarea.copy(done = !tarea.done)
        }
    }

    fun editarTarea(tarea: Tarea) {
        val index = tareas.indexOfFirst { it.id == tarea.id }
        if (index != -1) {
            tareas[index] = tarea
        }
    }

    fun eliminarTarea(tarea: Tarea) {
        tareas.remove(tarea)
    }
}
