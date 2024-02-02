package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class Product(
    val active_ingredients: List<ActiveIngredient>,
    val brand_name: String,
    val dosage_form: String,
    val marketing_status: String,
    val product_number: String,
    val reference_drug: String,
    val reference_standard: String,
    val route: String,
    val te_code: String
)