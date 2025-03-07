package com.example.app_webservice.data.api

import kotlin.random.Random
//protocolos que odoo necesita para hacer la peticion
data class JsonRpcRequest(
    //version del protocolo
    val jsonrpc: String = "2.0",
    //metodo estandar-->"call"
    val method: String,
    //parametros que conforman a que parte de la bdd quiero llegar y que hacer
    val params: Map<String, Any>,

    val id: Int = Random.nextInt(0,1000000)

)
