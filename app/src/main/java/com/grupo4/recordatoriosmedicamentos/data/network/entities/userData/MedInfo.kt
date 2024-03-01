package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class MedInfo(
    val id:String,
    val dosis:String?=null,
    val frecuencia:String?=null,
    val f_inicio:String?=null,
    val hora_inicio:String?=null,
    val caducidad :String?=null,
    val indicaciones:String?=null,
    val observaciones:String?=null,
    val medId:String?=null
)
{
    constructor() : this("")
}
