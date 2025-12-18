package com.example.et_clearapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.R
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo
import com.example.et_clearapp.viewmodel.EstadoAnimoViewModel
private data class Mood(
    val key: String,
    val label: String,
    val drawableRes: Int
)

@Composable
private fun moodsCatalog(): List<Mood> = listOf(
    Mood("calmado", "Calmado", R.drawable.calmado),
    Mood("confundido", "Confundido", R.drawable.confundido),
    Mood("triste", "Triste", R.drawable.triste),
    Mood("feliz", "Feliz", R.drawable.feliz),
)

@Composable
fun EstadoAnimo(
    viewModel: EstadoAnimoViewModel,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()
    val moods = moodsCatalog()
    val topInset = paddingValues.calculateTopPadding()
    val bottomInset = paddingValues.calculateBottomPadding()


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = topInset + 16.dp,
                bottom = bottomInset + 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cómo me siento hoy",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(8.dp))

        val filas = moods.chunked(2)
        filas.forEachIndexed { index, fila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val m1 = fila[0]
                MoodCard(
                    mood = m1,
                    selected = m1.key == uiState.moodSeleccionado,
                    onClick = { viewModel.onMoodSelected(m1.key) },
                    modifier = Modifier.weight(1f)
                )

                if (fila.size > 1) {
                    val m2 = fila[1]
                    MoodCard(
                        mood = m2,
                        selected = m2.key == uiState.moodSeleccionado,
                        onClick = { viewModel.onMoodSelected(m2.key) },
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
            }

            if (index != filas.lastIndex) {
                Spacer(Modifier.height(8.dp))
            }
        }

        if (uiState.errorMood != null) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = uiState.errorMood ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(12.dp))

        Text(
            text = "¿Por qué te sientes así?",
            style = MaterialTheme.typography.titleSmall
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.comentario,
            onValueChange = { nuevoTexto ->
                viewModel.onComentarioChange(nuevoTexto)
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            minLines = 3,
            label = { Text("Escribe aquí...") },
            isError = uiState.errorComentario != null
        )

        if (uiState.errorComentario != null) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = uiState.errorComentario ?: "",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.guardarEntrada()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeFondo,
                contentColor = CremaTexto
            ),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
        ) {
            Text(
                text = "Guardar entrada",
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}


@Composable
private fun MoodCard(
    mood: Mood,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fondo = if (selected) {
        VerdeFondo.copy(alpha = 0.6f)
    } else {
        VerdeFondo
    }

    Column(
        modifier = modifier
            .height(150.dp)
            .background(
                color = fondo,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = mood.drawableRes),
            contentDescription = mood.label,
            modifier = Modifier.size(64.dp)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = mood.label,
            color = CremaTexto,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyMedium
        )

        if (selected) {
            Spacer(Modifier.height(4.dp))
            Text(
                text = "✓ seleccionado",
                color = CremaTexto,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
