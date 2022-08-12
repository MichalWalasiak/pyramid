package com.mwalasiak.pyramid.api

import kotlinx.serialization.SerialName

@KSerializable
data class LottoApiResponse(
    @SerialName("Lotto")
    val lotto: Lotto
) {

    @KSerializable
    data class Lotto(
        val numerki: String,
        val num_losowania: String
    )
}
