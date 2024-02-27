package com.grupo4.recordatoriosmedicamentos.logic.usercases.fda

import android.util.Log
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.core.getFullInfoDrugLG
import com.grupo4.recordatoriosmedicamentos.data.network.endpoints.InfoDrugEndPoin
import com.grupo4.recordatoriosmedicamentos.data.network.repository.retrofit.RetrofitBase
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG

class FdaGetInfoDrugUserCase {

    suspend fun invoke(idDrug: String) : Result<FullInfoDrugsLG> {
        var result: Result<FullInfoDrugsLG>?= null
        var infoDrug = FullInfoDrugsLG()

        try {
            val baseService = RetrofitBase.getRetrofitFDAConnection()
            val service = baseService.create(InfoDrugEndPoin::class.java)
            val call = service.getDrugFullInfo("openfda.spl_id:$idDrug")


            if(call.isSuccessful){
                val a = call.body()!!
                infoDrug = a.results.first().openfda.getFullInfoDrugLG()
                result = Result.success(infoDrug) // POSIBLE ERROR
            }else{
                Log.d(Constants.TAG, "Error en el llamado del API Jikan")
                result = Result.failure(Exception("Error en el llamado del API Jikan"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
        }

        return result!!

    }

}