package com.example.app_webservice.data.repository


import com.example.app_webservice.data.api.JsonRpcRequest
import com.example.app_webservice.data.api.OdooApi
import com.example.app_webservice.session.SessionManager
import com.google.gson.JsonElement



class ReservaRepository(private val api: OdooApi) {

    suspend fun crearReserva(
        tipoServicio: String,
        fecha: String,
        horas: Int,
        ubicacion: String,
        precio: Int
    ): JsonElement? {
        return try {
            val uid = SessionManager.loggedInUserId
            val password = SessionManager.loggedInPassword

            if (uid == null || password == null) {
                println("❌ UID o contraseña no disponibles.")
                return null
            }

            val request = JsonRpcRequest(
                jsonrpc = "2.0",
                method = "call",
                params = mapOf(
                    "service" to "object",
                    "method" to "execute_kw",
                    "args" to listOf(
                        "TotalShine",        // nombre de la base de datos
                        uid,                 // ID del usuario autenticado
                        password,            // ✅ contraseña del usuario autenticado
                        "reserva.servicio",  // modelo
                        "create",            // método
                        listOf(
                            mapOf(
                                "tipo_servicio" to tipoServicio,
                                "fecha" to fecha,
                                "horas" to horas,
                                "ubicacion" to ubicacion,
                                "precio" to precio,
                                "usuario_id" to uid
                            )
                        )
                    )
                ),
                id = 1
            )

            val response = api.jsonrpc(request)
            println("✅ Reserva enviada: $response")
            response
        } catch (e: Exception) {
            println("❌ Error al crear la reserva: ${e.message}")
            null
        }
    }
}

