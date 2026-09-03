package com.example.cst438project1.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val logging = HttpLoggingInterceptor { message ->
        // Simple redaction for API key in logs
        val redactedMessage = message.replace(Regex("api_key=[^&]+"), "api_key=REDACTED")
        println(redactedMessage)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val apiService: LastFmApiService by lazy {
        Retrofit.Builder()
            .baseUrl(LastFmApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LastFmApiService::class.java)
    }
}
