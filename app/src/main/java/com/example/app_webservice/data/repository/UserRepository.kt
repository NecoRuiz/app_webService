package com.example.app_webservice.data.repository

import com.example.app_webservice.data.api.JsonRpcRequest
import com.example.app_webservice.data.api.JsonRpcResponse
import com.example.app_webservice.data.api.OdooApi
import com.google.gson.JsonElement

class UserRepository(private val api: OdooApi) {

    suspend fun getUserId(): Int?{
        val request = JsonRpcRequest(
            jsonrpc = "2.0",//versión de protocolo
            method = "call",//call--->execute_kw
            params = mapOf(
                "service" to "common",//decimos a odoo que vamos a usar funcionalidades simples(autenticación)
                "method" to "authenticate",//función nativa de odoo
                "args" to listOf("TotalShine", "clienteprueba@ejemplo.com", "a", emptyMap<String, Any>())
            ),
            id = 1//id de la petición

            )
            val response = api.jsonrpc(request)
            return response.asJsonObject["result"]?.asInt//devuelve el uid si es válido
    }

    suspend fun registerUser(name: String, email: String, password: String): JsonElement? {
        return try {
            val uid = getUserId()
            if (uid == null) {
                println("Error: No se pudo obtener el UID del usuario autenticado.")
                return null
            } // Obtiene el UID antes de ejecutar métodos dentro de res.users
            val request = JsonRpcRequest(
                jsonrpc = "2.0",//versión de protocolo
                method = "call",//call--->execute_kw
                params = mapOf(
                    "service" to "object",//decimos a odoo que vamos a trabajar sobre objetos
                    "method" to "execute_kw",//funcion nativa de odoo
                    "args" to listOf(
                        "TotalShine",//bdd
                        uid,  // Ahora usamos el UID obtenido
                        "a",//pass
                        "res.users",//modelo afectado
                        "create_user_as_sudo",//función personalizada
                        listOf(
                            mapOf(
                                "name" to name,//nuevo nombre
                                "login" to email,//nuevo email
                                "password" to password//nueva pass
                            )
                        )
                    )
                ),
                id = 1
            )
            println("Enviando solicitud a Odoo: $request")
            val response = api.jsonrpc(request)
            println("Respuesta de Odoo: $response")

            return response
        } catch (e: Exception) {
            println("❌ Error al conectar con Odoo: ${e.message}")
            null
        }
    }

    suspend fun authenticateUser(email: String, password: String): Int?{

        val request = JsonRpcRequest(
            jsonrpc = "2.0",
            method = "call",
            params = mapOf(
                "service" to "common",
                "method" to "authenticate",
                "args" to listOf("TotalShine", email, password, emptyMap<String, Any>())
            ),
            id = 1
        )
        val response = api.jsonrpc(request)
        return response.asJsonObject["result"]?.asInt
    }
}

