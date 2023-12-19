package com.example.ejerciciosexoplayer.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.exoplayer.ExoPlayer
import com.example.ejerciciosexoplayer.R
import com.example.ejerciciosexoplayer.shared.ExoPlayerViewModel
import com.example.ejerciciosexoplayer.shared.ScaffoldViewModel

@Composable
fun ExoPlayerScreen(viewModelScaffold: ScaffoldViewModel = viewModel()){
    val contexto = LocalContext.current

    /* Variables de estado */
    val exoPlayerViewModel: ExoPlayerViewModel = viewModel()
    val duracion by exoPlayerViewModel.duracion.collectAsStateWithLifecycle()
    val posicion by exoPlayerViewModel.progreso.collectAsStateWithLifecycle()

    /* TODO: Llamar a crearExoPlayer y hacerSonarMusica */

    LaunchedEffect(Unit) {
        exoPlayerViewModel.crearExoPlayer(contexto)
        exoPlayerViewModel.hacerSonarMusica(contexto)
    }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Image(
            painter = painterResource(id = viewModelScaffold.cancionActual.collectAsState().value),
            contentDescription =""
        )
        Text("La duración  de este temazo es ${duracion/1000} " +
                "y vamos por el segundo ${posicion/1000}")

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = {  }) {
                Icon(Icons.Default.ArrowLeft, contentDescription = "")
            }
            IconButton(onClick = {
                exoPlayerViewModel.CambiarAnteriorCancion(contexto);
                viewModelScaffold.modificarAnteriorCancion()
            }) {
                Icon(Icons.Default.ArrowLeft, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.PausarOSeguirMusica() }) {
                Icon(Icons.Default.PlayArrow, contentDescription = "")
            }
            IconButton(onClick = {
                exoPlayerViewModel.CambiarSiguienteCancion(contexto);
                viewModelScaffold.modificarSiguienteCancion()
            }) {
                Icon(Icons.Default.ArrowRight, contentDescription = "")
            }
            IconButton(onClick = {  }) {
                Icon(Icons.Default.ArrowRight, contentDescription = "")
            }
        }
    }
}