package com.grupo4.recordatoriosmedicamentos.data.network.endpoints

import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.ResultDrugs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface InfoDrugEndPoin {

    @GET("?search=openfda.spl_id:{id}")
    suspend fun getDrugFullInfo(@Path("id") idDrug: String): Response<ResultDrugs>

}