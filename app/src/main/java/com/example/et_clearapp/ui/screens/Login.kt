@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.et_clearapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.network.LoginRequest
import com.example.et_clearapp.network.RetrofitInstance
import com.example.et_clearapp.session.SessionManager
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Composable
fun Login(
    onLoginExitoso: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mostrarError by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Iniciar sesión",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = CremaTexto
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdeFondo,
                    titleContentColor = CremaTexto
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Bienvenida/o!", style = MaterialTheme.typography.titleMedium)

            Text(
                "Inicia sesión para empezar a registrar tus tareas.",
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    mostrarError = false
                },
                label = { Text("Correo") },
                singleLine = true,
                enabled = !cargando,
                isError = mostrarError && email.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    mostrarError = false
                },
                label = { Text("Contraseña") },
                singleLine = true,
                enabled = !cargando,
                visualTransformation = PasswordVisualTransformation(),
                isError = mostrarError && password.isBlank(),
                modifier = Modifier.fillMaxWidth()
            )

            if (mostrarError) {
                Text(
                    "Credenciales inválidas o error de conexión.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Button(
                onClick = {
                    val formatoCorreoOk = email.contains("@")
                    val passLargaOk = password.length >= 4

                    if (!formatoCorreoOk || !passLargaOk) {
                        mostrarError = true
                        return@Button
                    }

                    scope.launch {
                        cargando = true
                        mostrarError = false

                        try {
                            val u = RetrofitInstance.usuariosApi.login(
                                LoginRequest(email = email, password = password)
                            )

                            SessionManager.setUser(u)

                            cargando = false
                            onLoginExitoso()
                        } catch (e: HttpException) {
                            cargando = false
                            mostrarError = true
                        } catch (e: Exception) {
                            cargando = false
                            mostrarError = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = !cargando,
                colors = ButtonDefaults.buttonColors(
                    containerColor = VerdeFondo,
                    contentColor = CremaTexto
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                if (cargando) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(22.dp)
                    )
                } else {
                    Text("Entrar")
                }
            }

            Text(
                text = "Demo sugerido (si corriste DataLoader):\nemail: fran@clear.cl\npassword: holis123",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Center
            )
        }
    }
}
