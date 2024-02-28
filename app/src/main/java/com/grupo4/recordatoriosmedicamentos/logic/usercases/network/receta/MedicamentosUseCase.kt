package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import android.util.Log
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.MedicamentoRepository

class MedicamentosUseCase {

    suspend fun getById(id:String): MedInfo?{
        return MedicamentoRepository().getMediByID(id).getOrNull()
    }

    suspend fun Save(
        idReceta:String,
        dosis: String,
        frecuencia: String,
        f_inicio: String,
        hora_inicio:String,
        caducidad: String,
        indicaciones: String,
        observaciones: String,
        medId: String
    ): MedInfo? {
        return MedicamentoRepository().saveMediDB(
            idReceta,
            dosis,
            frecuencia,
            f_inicio,
            hora_inicio,
            caducidad,
            indicaciones,
            observaciones,
            medId
        ).getOrNull()
    }

    suspend fun getAll(): List<MedInfo>? {
        Log.d("UCE",MedicamentoRepository().getAllMedi()!!.size.toString())
        return MedicamentoRepository().getAllMedi()
    }
}