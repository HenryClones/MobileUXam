package com.example.mobileuxam.api

import com.example.mobileuxam.Score
import com.example.mobileuxam.Results
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH

interface LeaderboardAPI {
    @PATCH("/api/results")
    suspend fun sendScore(@Body score: Score)

    @GET("/api/results")
    suspend fun getResults(): Results
}