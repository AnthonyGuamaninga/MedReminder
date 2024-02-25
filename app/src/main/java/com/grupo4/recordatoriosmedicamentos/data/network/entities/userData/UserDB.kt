package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class UserDB(
    val id:String,
    val email:String,
    val name: String,
    val receta: List<Receta>?=null
)
