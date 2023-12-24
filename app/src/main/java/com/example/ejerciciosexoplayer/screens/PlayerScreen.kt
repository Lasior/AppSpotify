package com.example.ejerciciosexoplayer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp,0.dp)
    ){
        Text(text = exoPlayerViewModel.cancionTitulo.value, fontSize = 30.sp)
        Image(
            painter = painterResource(id = exoPlayerViewModel.cancionImagen.collectAsState().value),
            contentDescription ="",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                    .size(400.dp)
        )
        Column {
            Slider(
                value = (posicion/1000).toFloat(),
                onValueChange = { (posicion/1000).toFloat() },
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.secondary,
                    activeTrackColor = MaterialTheme.colorScheme.secondary,
                    inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                steps = 1,
                valueRange = 0f..(duracion/1000).toFloat()

            )
            Row() {
                Text(text = "${posicion/1000}", modifier = Modifier.padding(start = 15.dp))
                Text(text = "${duracion/1000}", modifier = Modifier.padding(start = 280.dp))
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { exoPlayerViewModel.ActivarDesactivarRandomCancion() }) {
                Icon(painter = painterResource(id = R.drawable.iconrandom),contentDescription = "",
                    modifier = Modifier.aspectRatio(0.5F)
                )
            }
            IconButton(onClick = {
                exoPlayerViewModel.CambiarAnteriorCancion(contexto);
            }) {
                Icon(Icons.Default.ArrowLeft, contentDescription = "")
            }
            IconButton(onClick = {
                exoPlayerViewModel.PausarOSeguirMusica()
            }) {
                Icon(exoPlayerViewModel.iconoPlayPause.value, contentDescription = "")
            }
            IconButton(onClick = {
                exoPlayerViewModel.CambiarSiguienteCancion(contexto);
            }) {
                Icon(Icons.Default.ArrowRight, contentDescription = "")
            }
            IconButton(onClick = { exoPlayerViewModel.ActivarDesactivarBucleCancion() }) {
                Icon(painter = painterResource(id = R.drawable.iconbucle),contentDescription = "",
                    modifier = Modifier.aspectRatio(0.5F))
            }
        }
    }
}