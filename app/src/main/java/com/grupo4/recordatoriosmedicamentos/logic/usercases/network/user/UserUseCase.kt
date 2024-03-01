package com.grupo4.recordatoriosmedicamentos.logic.usercases.network.user

import com.grupo4.recordatoriosmedicamentos.data.network.entities.userData.UserDB
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.AuthenticationUserRepo
import com.grupo4.recordatoriosmedicamentos.data.network.repository.firestore.UserRepository

class UserUseCase {
    suspend fun getById(id:String): UserDB?{
        return UserRepository().getUserByID(id)
    }
    suspend fun save(id:String, email: String, name: String, lastname: String, edad:Int): UserDB?{
        return UserRepository().saveUserDB(id, email, name, lastname, edad).getOrNull()
    }
    suspend fun singIn(email:String, passwor:String): UserDB? {
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

    suspend fun update(user:UserDB){
        UserRepository().updateUserDB(user)
    }
}