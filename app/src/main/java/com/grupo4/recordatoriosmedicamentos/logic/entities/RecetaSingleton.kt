package com.grupo4.recordatoriosmedicamentos.logic.entities

import android.util.Log
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedInfo
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.MedTemp

class RecetaSingleton private constructor(){

    companion object{
        val instance : RecetaSingleton by lazy { RecetaSingleton() }
    }
    var listaMed: MutableList<MedInfo> = ArrayList()
    var temporal = MedTemp()

    fun agregarMed(med: MedTemp) {
        val nuevo = MedInfo(
            med.id,
            med.dosis,
            med.frecuencia,
            med.f_inicio,
            med.hora_inicio,
            med.caducidad,
            med.indicaciones,
            med.observaciones,
            med.medId
        )
        listaMed.add(nuevo)
    }

}