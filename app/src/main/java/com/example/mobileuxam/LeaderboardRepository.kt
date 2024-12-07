package com.example.mobileuxam

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import com.example.mobileuxam.api.LeaderboardAPI
import com.example.mobileuxam.api.LeaderboardAdapter

const val ENDPOINT_URL = "https://example.com/";

class LeaderboardRepository {
    private val leaderboardAPI: LeaderboardAPI

    init {

        val moshiConfig = Moshi.Builder()
            .add(LeaderboardAdapter())
            .build()

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiConfig))
            .client(okHttpClient)
            .build()

        leaderboardAPI = retrofit.create()
    }

    suspend fun getAverageGood(): Float {
        return leaderboardAPI.getAverageGood()
    }

    suspend fun getAverageBad(): Float {
        return leaderboardAPI.getAverageBad()
    }

    suspend fun sendScore(score: Score): Unit {
        leaderboardAPI.sendScore(score)
    }
}