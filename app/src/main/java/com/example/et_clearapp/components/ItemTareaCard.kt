package com.example.et_clearapp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.model.Tarea
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo

@Composable
fun ItemTareaCard(
    tarea: Tarea,
    onToggle: (Tarea) -> Unit,
    onEditar: (Tarea) -> Unit,
    onEliminar: (Tarea) -> Unit,
    modifier: Modifier = Modifier
) {
    var menuAbierto by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = VerdeFondo,
            contentColor = CremaTexto,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = tarea.done,
                onCheckedChange = { onToggle(tarea) }
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            ) {
                Text(
                    tarea.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = CremaTexto
                )

                val detalle = "Prioridad: ${tarea.prioridad.name} • Fecha: ${tarea.dueDate}"
                Text(
                    detalle,
                    style = MaterialTheme.typography.bodySmall,
                    color = CremaTexto
                )
            }

            Box {
                IconButton(onClick = { menuAbierto = true }) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        tint = CremaTexto
                    )
                }

                DropdownMenu(
                    expanded = menuAbierto,
                    onDismissRequest = { menuAbierto = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Editar") },
                        onClick = {
                            menuAbierto = false
                            onEditar(tarea)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Eliminar") },
                        onClick = {
                            menuAbierto = false
                            onEliminar(tarea)
                        }
                    )
                }
            }
        }
    }
}
