package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.UserRepository

class SaveUserInDBUserCase {
    suspend fun invoke(id:String, email: String, name: String, lastname: String, edad:Int): UserDB?{
        return UserRepository().saveUserDB(id, email, name, lastname, edad).getOrNull()
    }
}