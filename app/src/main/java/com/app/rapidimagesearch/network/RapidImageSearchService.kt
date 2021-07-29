package com.app.rapidimagesearch.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RapidImageSearchService {
    @GET("ImageSearchAPI")
    suspend fun search(
        @Header("x-rapidapi-key")
        apiKey: String,
        @Header("x-rapidapi-host")
        host: String,
        @Query("q")
        query: String,
        @Query("pageNumber")
        pageNumber: Int,
        @Query("pageSize")
        pageSize: Int,
        @Query("autoCorrect")
        autoCorrect: Boolean
    ): RapidImageSearchResponse
}