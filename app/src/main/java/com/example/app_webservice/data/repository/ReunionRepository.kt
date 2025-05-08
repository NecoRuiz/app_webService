package com.example.app_webservice.data.repository

import com.example.app_webservice.data.api.JsonRpcRequest
import com.example.app_webservice.data.api.OdooApi
import com.example.app_webservice.session.SessionManager
import com.google.gson.JsonElement

class ReunionRepository(private val api: OdooApi) {

    suspend fun crearSolicitudReunion(fecha: String, hora: String, motivo: String, estado: String): JsonElement? {
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
                        "TotalShine",
                        uid,
                        password,
                        "solicitud.reunion",
                        "create",
                        listOf(
                            mapOf(
                                "fecha" to fecha,
                                "hora" to hora,
                                "motivo" to motivo,
                                "estado" to estado,
                                "usuario_id" to uid
                            )
                        )
                    )
                ),
                id = 1
            )

            val response = api.jsonrpc(request)
            println("✅ Reunión solicitada: $response")
            response
        } catch (e: Exception) {
            println("❌ Error al solicitar reunión: ${e.message}")
            null
        }
    }
}
