package com.mecharium.articlux_1.data.remote

import androidx.core.location.LocationRequestCompat
import com.mecharium.articlux_1.data.model.ArticleResponse
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

    @GET("api/get_articles")
    suspend fun getArticles(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 60
    ) : ArticleResponse
}