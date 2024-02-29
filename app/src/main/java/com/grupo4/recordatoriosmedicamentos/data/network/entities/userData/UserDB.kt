package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class UserDB(
    val id:String,
    val email:String,
    val name: String?=null,
    val lastname: String?=null,
    val edad:Int?=null,
    val recetasId: MutableList<String>?=null
){
    constructor() : this("","")
}
