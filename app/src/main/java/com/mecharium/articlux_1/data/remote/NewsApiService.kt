package com.mecharium.articlux_1.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

import com.mecharium.articlux_1.data.model.ProceedResponse
import com.mecharium.articlux_1.data.model.PaginatedArticles
import com.mecharium.articlux_1.data.model.ScanResponse


interface NewsApiService {
    @POST("api/scan")
    suspend fun scan(): Response<ScanResponse>

    @POST("api/proceed")
    suspend fun proceed() : Response<ProceedResponse>

    @GET("api/get_articles")
    suspend fun getArticles(
        @Query("page") page: Int
    ): PaginatedArticles
}