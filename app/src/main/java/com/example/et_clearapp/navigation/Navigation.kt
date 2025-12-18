package com.example.et_clearapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.et_clearapp.data.local.MoodDataStore
import com.example.et_clearapp.ui.screens.Agua
import com.example.et_clearapp.ui.screens.Diario
import com.example.et_clearapp.ui.screens.EstadoAnimo
import com.example.et_clearapp.ui.screens.Inicio
import com.example.et_clearapp.ui.screens.Login
import com.example.et_clearapp.ui.screens.Tareas
import com.example.et_clearapp.viewmodel.AguaViewModel
import com.example.et_clearapp.viewmodel.EstadoAnimoViewModel
import com.example.et_clearapp.viewmodel.TareasViewModel
import com.example.et_clearapp.viewmodel.DiarioViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


sealed class Ruta(val path: String, val etiqueta: String) {
    data object Login : Ruta("login", "Login")
    data object Inicio : Ruta("inicio", "Inicio")
    data object Tareas : Ruta("tareas", "Tareas")
    data object Agua : Ruta("agua", "Agua")
    data object EstadoAnimo : Ruta("estado_animo", "Ánimo")
    data object Diario : Ruta("diario", "Diario")
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val tareasViewModel: TareasViewModel = viewModel()
    val aguaViewModel: AguaViewModel = viewModel()

    val context = LocalContext.current
    val repo = remember { MoodDataStore(context) }
    val estadoAnimoViewModel = remember { EstadoAnimoViewModel(repo) }
    val diarioViewModel: DiarioViewModel = viewModel()


    NavHost(
        navController = navController,
        startDestination = Ruta.Login.path
    ) {

        composable(Ruta.Login.path) {
            Login(
                onLoginExitoso = {
                    navController.navigate(Ruta.Inicio.path) {
                        popUpTo(Ruta.Login.path) { inclusive = true }
                    }
                }
            )
        }

        composable(Ruta.Inicio.path) {
            Inicio(
                irATareas = { navController.navigate(Ruta.Tareas.path) },
                irAAnimo = { navController.navigate(Ruta.EstadoAnimo.path) },
                irAAgua = { navController.navigate(Ruta.Agua.path) },
                irADiario = { navController.navigate(Ruta.Diario.path) },
                pendientesHoy = tareasViewModel.pendientesCount,
                completadasHoy = tareasViewModel.completadasCount,
                vasosHoy = aguaViewModel.vasos,
                paddingValues = paddingValues
            )
        }

        composable(Ruta.Tareas.path) {
            Tareas(
                viewModel = tareasViewModel,
                paddingValues = paddingValues
            )
        }

        composable(Ruta.Agua.path) {
            Agua(viewModel = aguaViewModel,
                paddingValues = paddingValues)
        }

        composable(Ruta.EstadoAnimo.path) {
            EstadoAnimo(
                viewModel = estadoAnimoViewModel,
                paddingValues = paddingValues
            )
        }

        composable(Ruta.Diario.path) {
            Diario(viewModel = diarioViewModel,
                paddingValues = paddingValues)
        }
    }
}