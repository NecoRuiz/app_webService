package com.example.app_webservice.data.api

import com.google.gson.JsonElement
import retrofit2.http.Body
import retrofit2.http.POST
//interfaz para gestionar las solicitudes HTTPS
//@POST
//@GET
//@PUT
//@DELETE
interface OdooApi {

    @POST("/jsonrpc")//endpoint comun para comunicarme con odoo Json
                        //envia un obj peticion de tipo JsonRpcRequest y devuelve un JsonElement
    suspend fun jsonrpc(@Body request: JsonRpcRequest): JsonElement

}