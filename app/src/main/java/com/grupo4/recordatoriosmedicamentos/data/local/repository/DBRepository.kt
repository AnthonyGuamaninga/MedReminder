package com.grupo4.recordatoriosmedicamentos.data.local.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo4.recordatoriosmedicamentos.data.local.dao.UsersDAO
import com.grupo4.recordatoriosmedicamentos.data.local.entities.Users

@Database(
    entities = [Users::class],
    version = 1
)
abstract class DBRepository: RoomDatabase() {
    abstract fun getUsersDAO() : UsersDAO
}

class DBConnection(){
    fun getConnection(context: Context) : DBRepository =
        Room.databaseBuilder(
            context,
            DBRepository::class.java,
            "DBTest"
        ).build()
}