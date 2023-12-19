package com.example.ejerciciosexoplayer.shared

import androidx.lifecycle.ViewModel
import com.example.ejerciciosexoplayer.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScaffoldViewModel : ViewModel() {

    private val _lista =  MutableStateFlow(lista)

    private val _indice  = MutableStateFlow(0)
    val indice = _indice.asStateFlow()

    private val _titulo = MutableStateFlow("Player")
    val titulo = _titulo.asStateFlow()

    // Función que actualiza el título del Top bar
    fun modificarTitulo(nuevoTitulo : String){
         _titulo.value = nuevoTitulo
    }

    private val _cancionActual = MutableStateFlow(_lista.value[indice.value].imagen)
    val cancionActual = _cancionActual.asStateFlow()

    fun modificarSiguienteCancion(){
        _indice.value++
        if (_indice.value == _lista.value.size) {
            _indice.value = 0
        }
        _cancionActual.value = _lista.value[indice.value].imagen
    }

    fun modificarAnteriorCancion(){
        _indice.value--
        if (_indice.value < 0) {
            _indice.value = _lista.value.size-1
        }
        _cancionActual.value = _lista.value[indice.value].imagen
    }
}