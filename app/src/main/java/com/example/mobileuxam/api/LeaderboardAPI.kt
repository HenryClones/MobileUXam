package com.example.mobileuxam.api

import com.example.mobileuxam.Score
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LeaderboardAPI {
    @POST("/")
    suspend fun sendScore(@Body score: Score)

    @GET("/")
    suspend fun getAverageBad(): Float

    @GET("/")
    suspend fun getAverageGood(): Float
}