package com.grupo4.recordatoriosmedicamentos.data.network.endpoints

import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.ResultDrugs
import retrofit2.Response
import retrofit2.http.GET

interface ResultDrugsEndPoint {

    @GET("?limit=94")
    suspend fun getResultDrugs(): Response<ResultDrugs>

}