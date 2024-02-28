package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class Receta(
    val id:String,
    val medicamentos:MutableList<String>,
    val activo:Boolean,
    val idUser: String
)
