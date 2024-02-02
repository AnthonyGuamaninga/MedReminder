package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class Result(
    val application_number: String,
    val openfda: Openfda,
    val products: List<Product>,
    val sponsor_name: String,
    val submissions: List<Submission>
)