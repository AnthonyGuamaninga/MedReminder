package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.UserRepository

class GetUserByIdUserCase {
    suspend fun invoke(id:String):UserDB?{
        return UserRepository().getUserByID(id)
    }
}