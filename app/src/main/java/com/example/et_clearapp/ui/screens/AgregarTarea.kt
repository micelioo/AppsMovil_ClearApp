package com.example.et_clearapp.ui.screens

import com.example.et_clearapp.model.Prioridad
import com.example.et_clearapp.model.Tarea
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarTarea(
    alCerrar: () -> Unit,
    alGuardar: (titulo: String, fecha: LocalDate, prioridad: Prioridad) -> Unit,
    esEdicion: Boolean = false,
    tareaInicial: Tarea? = null,
    alGuardarEdicion: ((id: Long, titulo: String, fecha: LocalDate, prioridad: Prioridad) -> Unit)? = null
) {
    var titulo by remember { mutableStateOf(tareaInicial?.title ?: "") }
    var fechaTexto by remember { mutableStateOf(tareaInicial?.dueDate?.toString() ?: "") }
    var prioridad by remember { mutableStateOf(tareaInicial?.prioridad ?: Prioridad.BAJA) }

    var mostrarDatePicker by remember { mutableStateOf(false) }

    val millisInicial = remember(tareaInicial) {
        tareaInicial?.dueDate
            ?.atStartOfDay(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = millisInicial
    )

    val formatter = remember { DateTimeFormatter.ISO_LOCAL_DATE }

    val fechaValida = remember(fechaTexto) {
        if (fechaTexto.isBlank()) {
            false
        } else {
            try {
                LocalDate.parse(fechaTexto, formatter)
                true
            } catch (_: DateTimeParseException) {
                false
            }
        }
    }

    val sePuedeGuardar = titulo.isNotBlank() && fechaValida

    AlertDialog(
        onDismissRequest = alCerrar,
        title = { Text(if (esEdicion) "Editar tarea" else "Nueva tarea") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(8.dp))

                OutlinedTextField(
                    value = fechaTexto,
                    onValueChange = {},
                    label = { Text("Fecha de la tarea") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = false,
                    isError = fechaTexto.isNotBlank() && !fechaValida
                )

                if (!fechaValida) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Seleccione una fecha válida",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(Modifier.height(4.dp))

                Button(
                    onClick = { mostrarDatePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar fecha")
                }

                Spacer(Modifier.height(16.dp))

                Text("Prioridad")
                Spacer(Modifier.height(4.dp))

                PrioridadSelector(
                    prioridadActual = prioridad,
                    onCambiar = { p -> prioridad = p }
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = sePuedeGuardar,
                onClick = {
                    val fechaLocalDate = LocalDate.parse(fechaTexto, formatter)

                    if (esEdicion && tareaInicial != null && alGuardarEdicion != null) {
                        val id = tareaInicial.id ?: 0L
                        alGuardarEdicion(
                            id,
                            titulo,
                            fechaLocalDate,
                            prioridad
                        )
                    } else {
                        alGuardar(
                            titulo,
                            fechaLocalDate,
                            prioridad
                        )
                    }
                    alCerrar()
                }
            ) {
                Text(if (esEdicion) "Guardar" else "Crear")
            }
        },
        dismissButton = {
            TextButton(onClick = alCerrar) {
                Text("Cancelar")
            }
        }
    )

    if (mostrarDatePicker) {
        DatePickerDialog(
            onDismissRequest = { mostrarDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millisSeleccionados = datePickerState.selectedDateMillis
                        if (millisSeleccionados != null) {
                            val fechaSeleccionada = Instant.ofEpochMilli(millisSeleccionados)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            fechaTexto = fechaSeleccionada.format(formatter)
                        }
                        mostrarDatePicker = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = true
            )
        }
    }
}

@Composable
private fun PrioridadSelector(
    prioridadActual: Prioridad,
    onCambiar: (Prioridad) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            RadioButton(
                selected = prioridadActual == Prioridad.ALTA,
                onClick = { onCambiar(Prioridad.ALTA) }
            )
            Text("Alta", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            RadioButton(
                selected = prioridadActual == Prioridad.MEDIA,
                onClick = { onCambiar(Prioridad.MEDIA) }
            )
            Text("Media", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            RadioButton(
                selected = prioridadActual == Prioridad.BAJA,
                onClick = { onCambiar(Prioridad.BAJA) }
            )
            Text("Baja", modifier = Modifier.padding(start = 8.dp))
        }
    }
}
