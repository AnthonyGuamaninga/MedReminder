package com.grupo4.recordatoriosmedicamentos.ui.core

import android.app.Application
import com.grupo4.recordatoriosmedicamentos.data.local.repository.DBConnection
import com.grupo4.recordatoriosmedicamentos.data.local.repository.DBRepository
import com.grupo4.recordatoriosmedicamentos.logic.usercases.local.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class My_Application: Application() {
    override fun onCreate() {
        super.onCreate()

        con = DBConnection().getConnection(applicationContext)

        GlobalScope.launch(Dispatchers.IO) {
            LoginUseCase(con).insertUser()
        }

    }

    companion object{
        private lateinit var con: DBRepository

        fun getConnectionDB(): DBRepository?{
            return con
        }
    }
}