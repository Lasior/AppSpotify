package com.example.ejerciciosexoplayer.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ejerciciosexoplayer.screens.BarraInferior
import com.example.ejerciciosexoplayer.screens.BarraSuperior
import com.example.ejerciciosexoplayer.screens.ExoPlayerScreen
import com.example.ejerciciosexoplayer.shared.ExoPlayerViewModel
import com.example.ejerciciosexoplayer.shared.Rutas
import com.example.ejerciciosexoplayer.shared.ScaffoldViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrafoNavegacion(){

    val navController = rememberNavController()

   // El parametro route nos devuelve, en formato string, la ruta actual.
    val entradaNavActual by navController.currentBackStackEntryAsState()

    val rutaActual = entradaNavActual?.destination?.route

    // View model general que controla diversos valores del Scaffold -> el título y la barra de navegación
    val viewModelScaffold : ScaffoldViewModel = viewModel()

    Scaffold(topBar = { BarraSuperior(titulo = "Spotify" /* TODO : Llamar la variable titulo, del VM de Scaffold*/)},
    bottomBar = { BarraInferior(funcionNavegarPlayer = {
                /* TODO : Hacer popupto */
            }
        , funcionNavegarFoto = {
            /*  TODO : Navegar hacia la pantalla de la Foto */
        })},
        content = {
            // paddingValues representa los dp que hay para evitar que el contenido se solape con las barras
            paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(navController = navController, startDestination = Rutas.Player.ruta){
                    composable(Rutas.Player.ruta){
                        ExoPlayerScreen(viewModelScaffold)
                    }

                    composable(Rutas.Foto.ruta){

                    }
                }
            }
        })
}
