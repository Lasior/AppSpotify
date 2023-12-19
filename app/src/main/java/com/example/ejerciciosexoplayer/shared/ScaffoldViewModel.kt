package com.example.ejerciciosexoplayer.shared

import androidx.lifecycle.ViewModel
import com.example.ejerciciosexoplayer.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScaffoldViewModel : ViewModel() {


    private val _titulo = MutableStateFlow("Player")
    val titulo = _titulo.asStateFlow()


    // Función que actualiza el título del Top bar
    fun modificarTitulo(nuevoTitulo : String){
         _titulo.value = nuevoTitulo
    }


    private val _cancionActual = MutableStateFlow(R.drawable.meteor_light_album)
    val cancionActual = _cancionActual.asStateFlow()


    // Función que actualiza la canción actual
    fun modificarCancion(){
        if(_cancionActual.value == R.drawable.meteor_light_album) _cancionActual.value = R.drawable.symphogear_xv_character_song_4
        else _cancionActual.value = R.drawable.meteor_light_album
    }
}