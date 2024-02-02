package com.grupo4.recordatoriosmedicamentos.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.grupo4.recordatoriosmedicamentos.data.local.entities.Users

@Dao
interface UsersDAO {

    @Query("select * from Users")
    fun getAllUsers() : List<Users>

    @Query("select * from Users where userId = :userId")
    fun getOneUser(userId :Int): Users

    @Insert
    fun insertUser(user: List<Users>)

    @Update
    fun updateUsers(users: List<Users>)


    //condicion delete
    @Delete
    fun delete(user: Users)
}