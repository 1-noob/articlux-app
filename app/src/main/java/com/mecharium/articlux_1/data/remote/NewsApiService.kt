package com.mecharium.articlux_1.data.remote

import retrofit2.Response
import retrofit2.http.GET


interface NewsApiService {

    @GET("api/ping")
    suspend fun ping(): Response<PingResponse>
}