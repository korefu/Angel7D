package com.korefu.angel7d.data

data class FormData(
    val name: String,
    val message: String,
    val contactInfo: String,
    val town: String,
    val type: FormType,
    val emailDestinations: List<EmailDestination>
)
