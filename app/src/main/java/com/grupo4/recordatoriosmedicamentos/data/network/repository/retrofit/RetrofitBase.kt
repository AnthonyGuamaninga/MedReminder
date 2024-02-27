package com.grupo4.recordatoriosmedicamentos.data.network.repository.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBase {

    private const val DRUGS_FDA_URL = "https://api.fda.gov/drug/drugsfda.json/"

    fun getRetrofitFDAConnection(): Retrofit{
        return Retrofit.Builder().baseUrl(DRUGS_FDA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}