package com.example.app_webservice.data.api

import kotlin.random.Random
//protocolos que odoo necesita para hacer la peticion
data class JsonRpcRequest(
    //version del protocolo
    val jsoconrpc: String = "2.0",
    //metodo que voy a ejecutar en odoo--->"execute_kw"
    val method: String,
    //parametros que conforman a que parte de la bdd quiero llegar y que hacer
    val params: Map<String, Any>,
    val id: Int = Random.nextInt(0,1000000)

)
//EJEMPLO
//val pedido = mapOf(--->CONSTRUCCION DEL ITEM
// "model" to "restaurant_order"
// "method" to "create"--->metodo que proporciona odoo
// "args" to listOf(mapOf("dish_name" to "Pizza", "quantity" to 2)
// )



//val request = JsonRcpRequest( --->esta val hace de PAQUETE dentro va el ITEM
//jsonrpc = "2.0" esto es siempre el mismo
//method = "execute_kw"
//params = pedido--->ITEM CONSTRUIDO
// )