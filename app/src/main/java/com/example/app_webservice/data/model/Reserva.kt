package com.example.app_webservice.data.model


data class Reserva(
    val tipoServicio: String,
    val fecha: String,
    val horas: Int,
    val ubicacion: String,
    val precio: Int
)
