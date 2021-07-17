package com.korefu.angel7d.api

import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Angel7DService {

    @FormUrlEncoded
    @POST("/contact-form-for-app.php")
    suspend fun sendMailData(
        @Field("name") name: String,
        @Field("message") message: String,
        @Field("email") contactInfo: String,
        @Field("city") city: String,
        @Field("title") formType: String,
        @Field("add") emailDestinations: String
    )

    companion object {
        private const val BASE_URL = "http://angel7d.ru"
        private var instance: Angel7DService? = null

        fun getInstance(): Angel7DService {
            if (instance == null)
                instance = create()
            return instance as Angel7DService
        }

        private fun create(): Angel7DService {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Angel7DService::class.java)
        }
    }
}