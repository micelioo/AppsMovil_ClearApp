package com.example.et_clearapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo

@Composable
fun ResumenDiaCard(
    pendientes: Int,
    completadas: Int,
    vasos: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = VerdeFondo,
            contentColor = CremaTexto
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Resumen de hoy",
                style = MaterialTheme.typography.titleMedium,
                color = CremaTexto
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "Pendientes: $pendientes",
                style = MaterialTheme.typography.bodyMedium,
                color = CremaTexto
            )
            Text(
                "Completadas: $completadas",
                style = MaterialTheme.typography.bodyMedium,
                color = CremaTexto
            )
            Text(
                "Agua: $vasos vasos",
                style = MaterialTheme.typography.bodyMedium,
                color = CremaTexto
            )
        }
    }
}
