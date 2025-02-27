package com.example.app_webservice.data.api

import com.google.gson.annotations.SerializedName
//respuesta que me da odoo-->error o resultado
data class JsonRpcResponse<T>(
    @SerializedName("result") val result: T,
    @SerializedName("error") val error: Error?
)
