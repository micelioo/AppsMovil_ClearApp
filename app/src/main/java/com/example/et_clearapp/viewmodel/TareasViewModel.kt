package com.example.et_clearapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.et_clearapp.model.Prioridad
import com.example.et_clearapp.model.Tarea
import java.time.LocalDate

class TareasViewModel : ViewModel() {

    var tareas: List<Tarea> by mutableStateOf(emptyList())
        private set

    var mostrarDialogo: Boolean by mutableStateOf(false)
        private set

    private var nextId: Long = 1L

    val pendientesCount: Int
        get() = tareas.count { !it.done }

    val completadasCount: Int
        get() = tareas.count { it.done }

    fun abrirDialogo() {
        mostrarDialogo = true
    }

    fun cerrarDialogo() {
        mostrarDialogo = false
    }

    fun agregarTarea(titulo: String, fecha: LocalDate, prioridad: Prioridad) {
        val nueva = Tarea(
            id = nextId++,
            title = titulo,
            dueDate = fecha,
            prioridad = prioridad,
            done = false
        )
        tareas = tareas + nueva
        mostrarDialogo = false
    }

    fun toggleTarea(tarea: Tarea) {
        val id = tarea.id ?: return
        tareas = tareas.map { t ->
            if (t.id == id) t.copy(done = !t.done) else t
        }
    }

    fun editarTarea(editada: Tarea) {
        val id = editada.id ?: return
        tareas = tareas.map { t ->
            if (t.id == id) editada else t
        }
    }

    fun eliminarTarea(tarea: Tarea) {
        val id = tarea.id ?: return
        tareas = tareas.filterNot { it.id == id }
    }
}