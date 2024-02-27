package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.RecetaRepository

class GetRecetaByIdUserCase {
    suspend fun invoke(id:String):Receta?{
        return RecetaRepository().getRecetaByID(id).getOrNull()
    }
}