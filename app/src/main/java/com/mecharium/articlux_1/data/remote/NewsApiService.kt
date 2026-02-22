package com.mecharium.articlux_1.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST


interface NewsApiService {

    @GET("api/ping")
    suspend fun ping(): Response<PingResponse>

    @POST("api/scan")
    suspend fun scan(): Response<ScanResponse>

    @POST("api/proceed")
    suspend fun proceed(): Response<ProceedResponse>
}