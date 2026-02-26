package com.mecharium.articlux_1.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface NewsApiService {

    @GET("api/ping")
    suspend fun ping(): Response<PingResponse>

    @POST("api/scan")
    suspend fun scan(): Response<ScanResponse>

    @POST("api/proceed")
    suspend fun proceed(): Response<ProceedResponse>

    @POST("api/review")
    suspend fun review(
        @Query("action") action: String,
        @Query("url") url: String? = null,
        @Query("category") category: String? = null
    ): Response<ReviewResponse>
}