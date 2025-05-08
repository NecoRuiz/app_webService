package com.example.app_webservice.ui.reserva

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_webservice.data.repository.ReservaRepository
import kotlinx.coroutines.launch

class ReservaViewModel(private val repository: ReservaRepository) : ViewModel() {
    private val _servicioSeleccionado = mutableStateOf<String?>(null)
    val servicioSeleccionado: State<String?> = _servicioSeleccionado

    private val _reservaExitosa = mutableStateOf<Boolean?>(null)
    val reservaExitosa: State<Boolean?> = _reservaExitosa

    val toastMessage = mutableStateOf("")


    fun setServicioSeleccionado(servicio: String) {
        _servicioSeleccionado.value = servicio
    }

    fun confirmarReserva(
        tipoServicio: String,
        fecha: String,
        horas: Int,
        ubicacion: String,
        precio: Int
    )
    {
        viewModelScope.launch {
            val tipoServicio = _servicioSeleccionado.value ?: "Desconocido"

            val resultado = repository.crearReserva(
                tipoServicio = tipoServicio,
                fecha = fecha,
                horas = horas,
                ubicacion = ubicacion,
                precio = precio
            )

            toastMessage.value = if (resultado != null) {
                "✅ Reserva enviada con éxito"
            } else {
                "❌ Error al enviar la reserva"
            }
        }
    }



}

