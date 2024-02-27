package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.AuthenticationUserRepo

class createUserCase {
    suspend fun invoke(email:String, password:String, name: String): UserDB? {
        var userDB : UserDB?=null
        AuthenticationUserRepo().createNewUser(email,password,name)
            .onSuccess {
                userDB=it
            }
            .onFailure {
                userDB=null
            }
        return userDB
    }
}