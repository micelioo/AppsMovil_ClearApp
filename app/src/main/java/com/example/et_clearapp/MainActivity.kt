package com.example.et_clearapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.et_clearapp.components.BottomBar
import com.example.et_clearapp.components.TopBar
import com.example.et_clearapp.navigation.AppNavigation
import com.example.et_clearapp.navigation.Ruta
import com.example.et_clearapp.ui.theme.ClearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ClearTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = backStackEntry?.destination?.route
                val mostrarBottomBar = currentRoute != Ruta.Login.path

                Scaffold(
                    topBar = {
                        TopBar(
                            showBack = currentRoute != Ruta.Inicio.path &&
                                    currentRoute != Ruta.Login.path,
                            onBack = { navController.navigateUp() }
                        )
                    },
                    bottomBar = {
                        if (mostrarBottomBar) {
                            BottomBar(
                                navController = navController,
                                currentRoute = currentRoute
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AppNavigation(
                            navController = navController,
                            paddingValues = innerPadding
                        )
                    }
                }
            }
        }
    }
}