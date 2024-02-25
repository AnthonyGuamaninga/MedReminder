package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class Receta(
    var id:String,
    val dosis:String?=null,
    val indicaciones:String?=null,
    val frecuencia:String?=null,
    val f_inicio:String?=null,
    val caducidad :String?=null,
    val observaciones:String?=null,
    val medId:String?=null
)
{
    constructor() : this("")
}
