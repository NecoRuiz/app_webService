package com.example.app_webservice.ui.reserva

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class ReservaViewModel : ViewModel() {

    private val _servicioSeleccionado = mutableStateOf<String?>(null)
    val servicioSeleccionado: State<String?> = _servicioSeleccionado

    fun setServicioSeleccionado(servicio: String) {
        _servicioSeleccionado.value = servicio
    }
}