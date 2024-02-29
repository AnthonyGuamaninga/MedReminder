package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class Receta(
    val id:String,
    val estado:Boolean,
    val medicamentos:MutableList<String>?=null,
    val idUser: String?=null,
    val fecha_Registro:String?=null
){
    constructor() : this("", false)
}
