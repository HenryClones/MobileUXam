package com.example.mobileuxam.api

import com.example.mobileuxam.Score
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LeaderboardAPI {
    @POST("/moment")
    suspend fun sendScore(@Body score: Score)

    @GET("/moment")
    suspend fun fetchScores(): List<Score>
}