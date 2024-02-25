package com.grupo4.recordatoriosmedicamentos.logic.usercases.fda

import android.util.Log
import com.grupo4.recordatoriosmedicamentos.core.Constants
import com.grupo4.recordatoriosmedicamentos.core.getFullInfoDrugLG
//import com.grupo4.recordatoriosmedicamentos.core.getFullInfoDrugLG
import com.grupo4.recordatoriosmedicamentos.data.network.endpoints.ResultDrugsEndPoint
import com.grupo4.recordatoriosmedicamentos.data.network.repository.retrofit.RetrofitBase
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG

class FdaGetResultDrugsUserCase {

    suspend fun invoke() : Result<List<FullInfoDrugsLG>> {
        var result: Result<List<FullInfoDrugsLG>>?= null
        val items = ArrayList<FullInfoDrugsLG>()

        try {
            val baseService = RetrofitBase.getRetrofitFDAConnection()
            val service = baseService.create(ResultDrugsEndPoint::class.java)
            val call = service.getResultDrugs()

            if(call.isSuccessful){
                val infoDrug = call.body()!!
                infoDrug.results.forEach{

                    if (it.openfda != null &&
                        it.openfda.spl_id != null &&
                        it.openfda.manufacturer_name != null &&
                        it.openfda.brand_name != null &&
                        it.openfda.generic_name != null &&
                        it.openfda.route != null) {
                        Log.d("TAG", it.openfda.toString())
                        items.add(it.openfda.getFullInfoDrugLG())
                    }
                }

                result = Result.success(items)
            }else{
                Log.e(Constants.TAG, "Error en el llamado del API openFDA")
                result = Result.failure(Exception("Error en el llamado del API openFDA"))
            }
        }catch (ex:Exception){
            Log.e(Constants.TAG, ex.stackTraceToString())
            result = Result.failure(Exception(ex))
        }

        return result!!
    }

}