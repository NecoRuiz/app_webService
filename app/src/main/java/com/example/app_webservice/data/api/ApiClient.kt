package com.example.app_webservice.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//patron singleton
//FUSION DE APICLIENT + ODOOAPI---->USERREPOSITORY---->VIEWMODEL
object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:8069")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
                            //retrofit(instacia de OdooApi con los metodos @POST @GET...)
    val odooApi: OdooApi = retrofit.create(OdooApi::class.java)
}