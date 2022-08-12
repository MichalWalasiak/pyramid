@file:OptIn(ExperimentalSerializationApi::class)

package com.mwalasiak.pyramid.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object RetrofitClientFactory {

    fun create(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClientFactory().client)
            .addConverterFactory(jsonMapper.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private val jsonMapper = Json {
        ignoreUnknownKeys = true
    }
}
