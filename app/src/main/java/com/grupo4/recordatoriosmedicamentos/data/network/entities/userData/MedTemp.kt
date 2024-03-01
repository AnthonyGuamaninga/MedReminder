package com.grupo4.recordatoriosmedicamentos.data.network.entities.userData

data class MedTemp(
    var id: String = "",
    var dosis: String = "",
    var frecuencia: String = "",
    var f_inicio: String = "",
    var hora_inicio: String = "",
    var caducidad: String = "",
    var indicaciones: String = "",
    var observaciones: String = "",
    var medId: String = ""
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
    )
}
