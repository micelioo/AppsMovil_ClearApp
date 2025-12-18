package com.example.et_clearapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.components.ItemTareaCard
import com.example.et_clearapp.model.Tarea
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo
import com.example.et_clearapp.viewmodel.TareasViewModel

@Composable
fun Tareas(
    viewModel: TareasViewModel,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val tareas = viewModel.tareas
    var tareaAEditar by remember { mutableStateOf<Tarea?>(null) }
    var tareaAEliminar by remember { mutableStateOf<Tarea?>(null) }
    val bottomInset = paddingValues.calculateBottomPadding()
    val topInset = paddingValues.calculateTopPadding()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = topInset + 16.dp,
                bottom = bottomInset + 80.dp
            )
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Tareas",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            if (tareas.isEmpty()) {
                Text(
                    text = "Aún no tienes tareas.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true),
                    contentPadding = PaddingValues(
                        top = 8.dp,
                        bottom = 96.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = tareas,
                        key = { it.id ?: 0L }
                    ) { t ->
                        ItemTareaCard(
                            tarea = t,
                            onToggle = { tarea -> viewModel.toggleTarea(tarea) },
                            onEditar = { tareaAEditar = it },
                            onEliminar = { tareaAEliminar = it }
                        )
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { viewModel.abrirDialogo() },
            containerColor = VerdeFondo,
            contentColor = CremaTexto,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Text("+")
        }
    }

    if (viewModel.mostrarDialogo) {
        AgregarTarea(
            alCerrar = { viewModel.cerrarDialogo() },
            alGuardar = { titulo, fecha, prioridad ->
                viewModel.agregarTarea(titulo, fecha, prioridad)
            }
        )
    }

    tareaAEditar?.let { t ->
        AgregarTarea(
            alCerrar = { tareaAEditar = null },
            alGuardar = { _, _, _ -> },
            esEdicion = true,
            tareaInicial = t,
            alGuardarEdicion = { id, titulo, fecha, prioridad ->
                viewModel.editarTarea(
                    Tarea(
                        id = id,
                        title = titulo,
                        dueDate = fecha,
                        prioridad = prioridad,
                        done = t.done
                    )
                )
                tareaAEditar = null
            }
        )
    }

    tareaAEliminar?.let { t ->
        AlertDialog(
            onDismissRequest = { tareaAEliminar = null },
            title = { Text("Eliminar tarea") },
            text = { Text("¿Seguro/a que quieres eliminar \"${t.title}\"?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.eliminarTarea(t)
                        tareaAEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { tareaAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
