package com.example.app_webservice.ui.reunion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_webservice.data.repository.ReunionRepository
import kotlinx.coroutines.launch

class ReunionViewModel(private val repository: ReunionRepository) : ViewModel() {

    private val _solicitudExitosa = mutableStateOf<Boolean?>(null)
    val solicitudExitosa: State<Boolean?> = _solicitudExitosa

    val toastMessage = mutableStateOf("")

    fun solicitarReunion(fecha: String, hora: String, motivo: String, estado: String = "pendiente") {
        viewModelScope.launch {
            val resultado = repository.crearSolicitudReunion(fecha, hora, motivo, estado)

            toastMessage.value = if (resultado != null) {
                "✅ Solicitud enviada con éxito"
            } else {
                "❌ Error al solicitar la reunión"
            }
        }
    }

    fun clearToastMessage() {
        toastMessage.value = ""
    }

}


