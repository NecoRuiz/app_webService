package com.example.app_webservice.data.repository

import com.example.app_webservice.data.api.JsonRpcRequest
import com.example.app_webservice.data.api.OdooApi
import com.example.app_webservice.data.model.Reunion
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

    suspend fun obtenerHistorialReuniones(): List<Reunion>? {
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
                        "TotalShine",            // Nombre de la base de datos
                        uid,                     // ID de usuario autenticado
                        password,                // Contraseña
                        "solicitud.reunion",     // Modelo
                        "search_read",           // Método
                        listOf(                  // Filtro de búsqueda (domain)
                            listOf(
                                listOf("usuario_id", "=", uid)
                            )
                        ),
                        mapOf(                   // Campos que quieres recuperar
                            "fields" to listOf("fecha", "hora", "motivo", "estado")
                        )
                    )
                ),
                id = 1
            )

            val response = api.jsonrpc(request)
            val result = response.asJsonObject["result"]?.asJsonArray ?: return emptyList()

            result.mapNotNull { item ->
                val obj = item.asJsonObject
                Reunion(
                    fecha = obj["fecha"]?.asString ?: "",
                    hora = obj["hora"]?.asString ?: "",
                    motivo = obj["motivo"]?.asString ?: "",
                    estado = obj["estado"]?.asString ?: ""
                )
            }

        } catch (e: Exception) {
            println("❌ Error al obtener historial de reuniones: ${e.message}")
            null
        }
    }


}
