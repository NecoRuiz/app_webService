package com.example.app_webservice.service

import com.example.app_webservice.api.JsonRpcRequest
import com.google.gson.JsonElement
import retrofit2.http.Body
import retrofit2.http.POST
//interfaz para gestionar las solicitudes HTTPS
//@POST
//@GET
//@PUT
//@DELETE
interface OdooApi {
//ejemplo
    @POST("jsonrpc")
                        //envia un obj peticion de tipo JsonRpcRequest y devuelve un JsonElement
    suspend fun jsonrpc(@Body request: JsonRpcRequest): JsonElement

}