package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import android.util.Log
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.RecetaRepository

class RecetaUseCase {
    suspend fun getById(id:String): Receta?{
        return RecetaRepository().getRecetaById(id).getOrNull()
    }

    suspend fun Save(id: String,estado:Boolean,medicamentos:List<String>,idUser:String, fecha:String):Receta?{
        return RecetaRepository().save(id, medicamentos, estado, idUser,fecha).getOrNull()
    }

    suspend fun getAll():List<Receta>?{
        return RecetaRepository().getAllReceta()
    }

    suspend fun getAllId(id:String):List<Receta>?{
        val list = RecetaRepository().getAllRecetaById(id).getOrNull()
        var listado : MutableList<Receta> = ArrayList()
        if(list!=null){
            for (doc in list){
                listado.add(doc.toObject<Receta>(Receta::class.java)!!)
            }
        }

        return listado.toList()
    }
}