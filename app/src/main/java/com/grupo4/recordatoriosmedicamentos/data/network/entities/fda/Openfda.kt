package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class Openfda(
    val application_number: List<String> ,
    val brand_name: List<String> ,
    val generic_name: List<String> ,
    val manufacturer_name: List<String> ,
    val nui: List<String>,
    val package_ndc: List<String>,
    val pharm_class_cs: List<String>,
    val pharm_class_epc: List<String>,
    val pharm_class_moa: List<String>,
    val pharm_class_pe: List<String>,
    val product_ndc: List<String>,
    val product_type: List<String>,
    val route: List<String> ,
    val rxcui: List<String>,
    val spl_id: List<String> ,
    val spl_set_id: List<String>  ,
    val substance_name: List<String>,
    val unii: List<String>
)