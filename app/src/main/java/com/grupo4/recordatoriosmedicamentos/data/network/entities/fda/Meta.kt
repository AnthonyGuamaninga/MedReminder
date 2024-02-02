package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class Meta(
    val disclaimer: String,
    val last_updated: String,
    val license: String,
    val results: Results,
    val terms: String
)