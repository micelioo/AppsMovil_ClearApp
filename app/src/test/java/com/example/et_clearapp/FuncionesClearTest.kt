package com.example.et_clearapp

import com.example.et_clearapp.model.Prioridad
import com.example.et_clearapp.model.Tarea
import com.example.et_clearapp.utils.calcularMililitrosAgua
import com.example.et_clearapp.utils.contarTareasCompletadas
import com.example.et_clearapp.utils.contarTareasPendientes
import com.example.et_clearapp.utils.cumpleMetaAgua
import com.example.et_clearapp.utils.esEntradaDiarioValida
import com.example.et_clearapp.utils.porcentajeTareasCompletadas
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class FuncionesClearTest : StringSpec({

    "contar tareas pendientes devuelve la cantidad correcta" {
        val tareas = listOf(
            Tarea(id = 1, title = "A", dueDate = LocalDate.now(), prioridad = Prioridad.ALTA, done = false),
            Tarea(id = 2, title = "B", dueDate = LocalDate.now(), prioridad = Prioridad.MEDIA, done = true),
            Tarea(id = 3, title = "C", dueDate = LocalDate.now(), prioridad = Prioridad.BAJA, done = false)
        )
        contarTareasPendientes(tareas) shouldBe 2
    }

    "contar tareas completadas devuelve la cantidad correcta" {
        val tareas = listOf(
            Tarea(id = 1, title = "A", dueDate = LocalDate.now(), prioridad = Prioridad.ALTA, done = true),
            Tarea(id = 2, title = "B", dueDate = LocalDate.now(), prioridad = Prioridad.MEDIA, done = true),
            Tarea(id = 3, title = "C", dueDate = LocalDate.now(), prioridad = Prioridad.BAJA, done = false)
        )
        contarTareasCompletadas(tareas) shouldBe 2
    }

    "porcentaje de tareas completadas sobre el total" {
        val tareas = listOf(
            Tarea(id = 1, title = "A", dueDate = LocalDate.now(), prioridad = Prioridad.ALTA, done = true),
            Tarea(id = 2, title = "B", dueDate = LocalDate.now(), prioridad = Prioridad.MEDIA, done = false),
            Tarea(id = 3, title = "C", dueDate = LocalDate.now(), prioridad = Prioridad.BAJA, done = true),
            Tarea(id = 4, title = "D", dueDate = LocalDate.now(), prioridad = Prioridad.BAJA, done = false)
        )
        porcentajeTareasCompletadas(tareas) shouldBe 50
    }

    "calcular mililitros totales de agua según los vasos" {
        calcularMililitrosAgua(4) shouldBe 1000
    }

    "cumple meta diaria de agua cuando se alcanzan los vasos mínimos" {
        cumpleMetaAgua(8) shouldBe true
        cumpleMetaAgua(5) shouldBe false
    }

    "valida que el texto del diario no sea demasiado corto" {
        esEntradaDiarioValida("Hoy me sentí bastante bien :)") shouldBe true
        esEntradaDiarioValida("hola") shouldBe false
    }
})
