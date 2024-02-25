package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.AuthenticationUserRepo

class singInUserCase {
    suspend fun invoke(email:String, passwor:String): UserDB? {
        var userDB : UserDB?=null
        AuthenticationUserRepo().SingInUsers(email,passwor)
            .onSuccess {
                userDB=it
            }
            .onFailure {
                userDB=null
            }
        return userDB
    }
}