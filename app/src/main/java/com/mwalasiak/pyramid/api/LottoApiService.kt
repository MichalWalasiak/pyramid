package com.mwalasiak.pyramid.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET

private const val BASE_URL = "https://www.lotto.pl/api/lotteries/draw-results/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface LottoApiService {
    @GET("/by-gametype?game=Szybkie600&index=1&size=10&sort=drawDate&order=DESC")
    suspend fun getLatestMultiMultiNumbers(): String
}

object LottoApi {
    val retrofitService : LottoApiService by lazy {
        retrofit.create(LottoApiService::class.java)
    }
}