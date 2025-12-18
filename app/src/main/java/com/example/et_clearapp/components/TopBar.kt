package com.example.et_clearapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.et_clearapp.ui.theme.DelaGothic
import com.example.et_clearapp.ui.theme.VerdeClaroTitulo
import com.example.et_clearapp.ui.theme.VerdeFondo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    showBack: Boolean,
    onBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Clear",
                style = MaterialTheme.typography.titleLarge,
                fontFamily = DelaGothic,
                color = VerdeClaroTitulo
            )
        },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Atrás",
                        tint = VerdeClaroTitulo
                    )
                }
            } else {
                Box(modifier = Modifier.size(48.dp))
            }
        },
        actions = {
            Box(modifier = Modifier.size(48.dp))
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = VerdeFondo
        )
    )
}
