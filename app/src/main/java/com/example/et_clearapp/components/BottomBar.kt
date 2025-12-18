package com.example.et_clearapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mood
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.et_clearapp.navigation.Ruta
import com.example.et_clearapp.ui.theme.CremaTexto
import com.example.et_clearapp.ui.theme.VerdeFondo

@Composable
fun BottomBar(
    navController: NavController,
    currentRoute: String?
) {
    val items = listOf(
        Triple(Ruta.Inicio, Icons.Filled.Home, "Inicio"),
        Triple(Ruta.Tareas, Icons.Filled.Checklist, "Tareas"),
        Triple(Ruta.Agua, Icons.Filled.WaterDrop, "Agua"),
        Triple(Ruta.EstadoAnimo, Icons.Filled.Mood, "Ánimo"),
        Triple(Ruta.Diario, Icons.Filled.Edit, "Diario")
    )

    NavigationBar(
        containerColor = VerdeFondo
    ) {
        items.forEach { (ruta, icon, labelOverride) ->
            val selected = currentRoute == ruta.path

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(ruta.path) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = labelOverride,
                        tint = CremaTexto
                    )
                },
                label = {
                    Text(
                        text = labelOverride,
                        color = CremaTexto
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CremaTexto,
                    selectedTextColor = CremaTexto,
                    unselectedIconColor = CremaTexto,
                    unselectedTextColor = CremaTexto,
                    indicatorColor = VerdeFondo
                )
            )
        }
    }
}