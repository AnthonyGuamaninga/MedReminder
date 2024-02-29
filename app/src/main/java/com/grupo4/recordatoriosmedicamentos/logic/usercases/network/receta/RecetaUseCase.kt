package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.RecetaRepository

class RecetaUseCase {
    suspend fun getById(id:String): Receta?{
        return RecetaRepository().getRecetaById(id).getOrNull()
    }

    suspend fun Save(id: String,estado:Boolean,medicamentos:MutableList<String>,idUser:String, fecha:String):Receta?{
        return RecetaRepository().save(id, medicamentos, estado, idUser,fecha).getOrNull()
    }

    suspend fun getAll():List<Receta>?{
        return RecetaRepository().getAllReceta()
    }
}