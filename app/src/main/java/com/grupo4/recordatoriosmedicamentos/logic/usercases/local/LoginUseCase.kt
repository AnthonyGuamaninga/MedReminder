package com.grupo4.recordatoriosmedicamentos.logic.usercases.local

import com.grupo4.recordatoriosmedicamentos.data.local.entities.Users
import com.grupo4.recordatoriosmedicamentos.data.local.repository.DBRepository
import com.grupo4.recordatoriosmedicamentos.data.local.repository.UserRepository

class LoginUseCase(val connection: DBRepository) {

    fun checkUserandPassword(user:String, password:String): Int{
        var ret = -1
        var users= UserRepository().getListUsers()
        val exist = users.filter {
            it.password==password && it.userName==user
        }
        if( exist.isNotEmpty()){
            ret = exist.first().userId
        }
        return ret
    }

    suspend fun getUserName1(userId: Int): Users =
        connection.getUsersDAO().getOneUser(userId)

    suspend fun getUserNAme(usrId:Int) : Users =
        UserRepository().getListUsers().first{
            it.userId == usrId
        }

    suspend fun insertUser()=
        if(connection.getUsersDAO().getAllUsers().isEmpty()){
            connection.getUsersDAO().insertUser(
                UserRepository().getListUsers()
            )
        }else{
            null
        }

    suspend fun getAllUsers() : List<Users> =
        connection.getUsersDAO().getAllUsers()

}