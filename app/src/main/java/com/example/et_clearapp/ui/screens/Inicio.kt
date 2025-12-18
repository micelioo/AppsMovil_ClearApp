package com.example.et_clearapp.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.components.NavCard
import com.example.et_clearapp.components.ResumenDiaCard
import com.example.et_clearapp.network.RetrofitInstance
import java.util.Calendar

@Composable
fun Inicio(
    irATareas: () -> Unit,
    irAAnimo: () -> Unit,
    irAAgua: () -> Unit,
    irADiario: () -> Unit,
    pendientesHoy: Int,
    completadasHoy: Int,
    vasosHoy: Int,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {

    // animación del header
    var headerVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { headerVisible = true }

    // saludo según la hora
    val saludo = remember {
        val h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        when (h) {
            in 5..11 -> "Buenos días!"
            in 12..18 -> "Buenas tardes!"
            else -> "Buenas noches!"
        }
    }

    // tarjetas de navegación
    val cards = remember(irATareas, irAAnimo, irAAgua, irADiario) {
        listOf(
            Triple("Tareas", Icons.Filled.Checklist, irATareas),
            Triple("Ánimo", Icons.Filled.Mood, irAAnimo),
            Triple("Agua", Icons.Filled.WaterDrop, irAAgua),
            Triple("Diario", Icons.Filled.Edit, irADiario)
        )
    }
    val bottomInset = paddingValues.calculateBottomPadding()
    val topInset = paddingValues.calculateTopPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = topInset + 16.dp,
                bottom = bottomInset + 80.dp
            ),
        horizontalAlignment = Alignment.Start
    ) {
        AnimatedVisibility(
            visible = headerVisible,
            enter = slideInVertically(
                initialOffsetY = { -40 },
                animationSpec = tween(400)
            ) + fadeIn(tween(400)),
            exit = slideOutVertically(
                targetOffsetY = { -40 },
                animationSpec = tween(200)
            ) + fadeOut(tween(200))
        ) {
            Column {
                Text(
                    text = saludo,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "¿Qué hacemos hoy?",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(Modifier.height(12.dp))

        // bloque de clima usando la API
        ClimaInicioSection()

        Spacer(Modifier.height(16.dp))

        // resumen del día (tareas + agua)
        ResumenDiaCard(
            pendientes = pendientesHoy,
            completadas = completadasHoy,
            vasos = vasosHoy,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // grid de tarjetas de navegación
        cards.chunked(2).forEach { fila ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val (t1, i1, a1) = fila[0]
                NavCard(
                    title = t1,
                    icon = i1,
                    onClick = a1,
                    modifier = Modifier.weight(1f)
                )

                if (fila.size > 1) {
                    val (t2, i2, a2) = fila[1]
                    NavCard(
                        title = t2,
                        icon = i2,
                        onClick = a2,
                        modifier = Modifier.weight(1f)
                    )
                } else {
                    Spacer(Modifier.weight(1f))
                }
            }

            Spacer(Modifier.height(12.dp))
        }
    }
}

@Composable
fun ClimaInicioSection(
    modifier: Modifier = Modifier
) {
    var temperature by remember { mutableStateOf<Double?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            error = null

            val apiKey = "ezOHlkSoXN86sZm3"
            val lat = "-33.4569"
            val lon = "-70.6483"
            val asl = 556

            val response = RetrofitInstance.climaApi.getWeather(
                apiKey = apiKey,
                lat = lat,
                lon = lon,
                asl = asl
            )

            val temp = response.data_1h?.temperature?.firstOrNull()
            if (temp != null) {
                temperature = temp
            } else {
                error = "Respuesta de clima inesperada"
            }
        } catch (e: Exception) {
            error = "Error al obtener el clima"
        } finally {
            isLoading = false
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        when {
            isLoading -> {
                Text(
                    text = "Cargando clima...",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            error != null -> {
                Text(
                    text = error!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            temperature != null -> {
                Text(
                    text = "Clima de hoy",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${"%.1f".format(temperature)} °C en Santiago",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Datos obtenidos desde la API de Meteoblue",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InicioPreview() {
    Inicio(
        irATareas = {},
        irAAnimo = {},
        irAAgua = {},
        irADiario = {},
        pendientesHoy = 1,
        completadasHoy = 2,
        vasosHoy = 3,
        paddingValues = PaddingValues(0.dp)
    )
}
