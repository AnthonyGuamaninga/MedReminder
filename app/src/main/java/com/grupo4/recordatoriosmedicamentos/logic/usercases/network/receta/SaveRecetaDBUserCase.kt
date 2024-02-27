package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.RecetaRepository

class SaveRecetaDBUserCase {
    suspend fun invoke(
        dosis: String,
        indicaciones: String,
        frecuencia: String,
        f_inicio: String,
        caducidad: String,
        observaciones: String,
        medId: String
    ): Receta? {
        return RecetaRepository().saveRecetaDB(
            dosis,
            indicaciones,
            frecuencia,
            f_inicio,
            caducidad,
            observaciones,
            medId
        ).getOrNull()
    }
}