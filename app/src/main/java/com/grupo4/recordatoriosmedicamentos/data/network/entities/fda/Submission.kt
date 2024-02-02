package com.grupo4.recordatoriosmedicamentos.data.network.entities.fda

data class Submission(
    val application_docs: List<ApplicationDoc>,
    val review_priority: String,
    val submission_class_code: String,
    val submission_class_code_description: String,
    val submission_number: String,
    val submission_public_notes: String,
    val submission_status: String,
    val submission_status_date: String,
    val submission_type: String
)