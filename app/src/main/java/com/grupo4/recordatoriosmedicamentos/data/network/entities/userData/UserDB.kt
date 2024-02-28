package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class UserDB(
    val id:String,
    val email:String,
    val name: String,
    val recetasId: MutableList<String>?=null
)
