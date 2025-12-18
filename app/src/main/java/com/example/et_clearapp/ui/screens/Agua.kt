package com.example.et_clearapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.ui.theme.VerdeFondo
import com.example.et_clearapp.viewmodel.AguaViewModel

@Composable
fun Agua(
    viewModel: AguaViewModel,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val vasos = viewModel.vasos
    val metaDiaria = 8
    val progreso = (vasos.toFloat() / metaDiaria.toFloat())
        .coerceIn(0f, 1f)

    val mensajeCoach = when {
        vasos == 0 -> "Toma tu primer vaso 💧"
        vasos in 1..3 -> "Bien, sigue hidratándote ✨"
        vasos in 4..7 -> "Casi llegas a la meta 🔥"
        vasos >= metaDiaria -> "¡Meta cumplida! 🙌"
        else -> ""
    }

    val topInset = paddingValues.calculateTopPadding()
    val bottomInset = paddingValues.calculateBottomPadding()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = topInset + 24.dp,
                bottom = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // título
        Text(
            text = "¿Cuánta agüita llevas?",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "$vasos vasos",
            style = MaterialTheme.typography.displaySmall
        )

        Text(
            text = "$vasos / $metaDiaria vasos",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction = progreso)
                        .height(10.dp)
                        .background(VerdeFondo)
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(
                text = mensajeCoach,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { viewModel.restarVaso() },
                enabled = vasos > 0
            ) {
                Text("-1 vaso")
            }
            Button(
                onClick = { viewModel.sumarVaso() }
            ) {
                Text("+1 vaso")
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "1 vaso = 250 ml aprox.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}