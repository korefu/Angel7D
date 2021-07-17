package com.korefu.angel7d.data

import com.korefu.angel7d.api.Angel7DService

class Angel7DRepository {
    private val service = Angel7DService.getInstance()
    suspend fun sendMessage(formData: FormData): Result<Unit> {
        return try {
            service.sendMailData(
                formType = formData.type.name.lowercase(),
                city = formData.town,
                contactInfo = formData.contactInfo,
                message = formData.message,
                name = formData.name,
                emailDestinations = listOfEmailsToString(formData.emailDestinations)
            )
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    private fun listOfEmailsToString(emailDestinations: List<EmailDestination>): String {
        val sb = StringBuilder()
        for (email in emailDestinations)
            sb.append(email.email + ", ")
        return sb.toString()
    }
}