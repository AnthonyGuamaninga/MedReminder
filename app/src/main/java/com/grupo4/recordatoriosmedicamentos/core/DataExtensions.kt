package com.grupo4.recordatoriosmedicamentos.core

import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.Openfda
import com.grupo4.recordatoriosmedicamentos.data.network.entities.fda.ResultDrugs
import com.grupo4.recordatoriosmedicamentos.logic.entities.FullInfoDrugsLG

fun Openfda.getFullInfoDrugLG() = FullInfoDrugsLG(
    this.spl_id.firstOrNull() ?: "",
    this.manufacturer_name.firstOrNull() ?: "",
    this.brand_name.firstOrNull() ?: "",
    this.generic_name.firstOrNull() ?: "",
    this.route.firstOrNull() ?: ""
)