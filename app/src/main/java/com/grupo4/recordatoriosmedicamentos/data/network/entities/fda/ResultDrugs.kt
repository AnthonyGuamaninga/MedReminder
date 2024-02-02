package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class ResultDrugs(
    val meta: Meta ?= null,
    val `results`: List<Result> = listOf()
)