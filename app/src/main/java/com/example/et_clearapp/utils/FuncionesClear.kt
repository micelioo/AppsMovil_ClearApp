package com.example.et_clearapp.utils

import com.example.et_clearapp.model.Tarea

fun contarTareasPendientes(tareas: List<Tarea>): Int {
    return tareas.count { !it.done }
}

fun contarTareasCompletadas(tareas: List<Tarea>): Int {
    return tareas.count { it.done }
}

fun porcentajeTareasCompletadas(tareas: List<Tarea>): Int {
    if (tareas.isEmpty()) return 0
    val completadas = contarTareasCompletadas(tareas)
    return ((completadas * 100.0) / tareas.size).toInt()
}

fun calcularMililitrosAgua(vasos: Int, mlPorVaso: Int = 250): Int {
    return vasos * mlPorVaso
}

fun cumpleMetaAgua(vasos: Int, metaVasos: Int = 8): Boolean {
    return vasos >= metaVasos
}

fun esEntradaDiarioValida(texto: String): Boolean {
    return texto.trim().length >= 10
}