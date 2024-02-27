package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.receta

import com.google.firebase.firestore.DocumentSnapshot
import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.Receta
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.RecetaRepository

class GetAllRecetaUserCase {
    suspend fun invoke(): List<Receta>? {
        return RecetaRepository().getAllRecetas()
    }
}