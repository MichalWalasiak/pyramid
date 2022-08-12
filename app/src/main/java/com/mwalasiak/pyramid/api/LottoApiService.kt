package com.mwalasiak.pyramid.api

import com.google.gson.JsonObject
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface LottoApiService {
    @GET("mapi_v6/index.php?json=getGames ")
    suspend fun getLatestMultiMultiNumbers(): LottoApiResponse
}
