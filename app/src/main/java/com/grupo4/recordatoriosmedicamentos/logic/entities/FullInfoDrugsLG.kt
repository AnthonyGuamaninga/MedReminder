package com.grupo4.recordatoriosmedicamentos.logic.entities

data class FullInfoDrugsLG(
    var spl_id: String = "",
    var manufacturer_name: String = "", // fabricate
    var brand_name: String = "", // marca o nombre comercial
    var generic_name: String= "", // nombre generico
    var route: String = "", // via de administracion

)
