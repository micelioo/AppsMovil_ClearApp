package com.example.et_clearapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import coil.compose.rememberAsyncImagePainter
import com.example.et_clearapp.viewmodel.DiarioViewModel

@Composable
fun Diario(
    viewModel: DiarioViewModel,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    var texto by rememberSaveable { mutableStateOf("") }
    var fotoUri by rememberSaveable { mutableStateOf<String?>(null) }

    // observamos las entradas para, al menos, mostrar cuántas hay
    val entradas = viewModel.entradas

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        fotoUri = uri?.toString()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Diario",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Escribe sobre tu día…") },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            minLines = 8
        )

        Spacer(Modifier.height(12.dp))

        if (fotoUri != null) {
            Image(
                painter = rememberAsyncImagePainter(model = fotoUri),
                contentDescription = "Foto del día",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            Spacer(Modifier.height(8.dp))
        } else {
            Text(
                text = "Puedes adjuntar una foto (opcional)",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
        }

        // botón para elegir foto
        Button(
            onClick = { pickImageLauncher.launch("image/*") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Adjuntar foto")
        }

        Spacer(Modifier.height(8.dp))

        // botón para guardar la entrada en el ViewModel
        Button(
            onClick = {
                if (texto.isNotBlank()) {
                    viewModel.diario(
                        texto = texto,
                        fotoUri = fotoUri
                    )
                    // limpiar formulario
                    texto = ""
                    fotoUri = null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar entrada")
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Entradas guardadas: ${entradas.size}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
