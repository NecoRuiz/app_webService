package com.example.app_webservice.ui.reserva

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_webservice.data.repository.ReservaRepository

class ReservaViewModelFactory(
    private val repository: ReservaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReservaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReservaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}