package com.example.mobileuxam

import androidx.work.Logger
import androidx.work.Logger.LogcatLogger
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import com.example.mobileuxam.api.LeaderboardAPI
import com.example.mobileuxam.api.LeaderboardAdapter
import retrofit2.HttpException
import java.io.IOException

const val ENDPOINT_URL = "http://projectxoanon.com:3000/";

class LeaderboardRepository {
    private val leaderboardAPI: LeaderboardAPI

    init {

        val moshiConfig = Moshi.Builder()
            .add(LeaderboardAdapter())
            .build()

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(ENDPOINT_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiConfig))
            .client(okHttpClient)
            .build()

        leaderboardAPI = retrofit.create()
    }

    suspend fun getResults(): Results {
        try {
            return leaderboardAPI.getResults()
        } catch (e: IOException) {
            LogcatLogger(0).warning("API", e.message ?: "Unknown error")
            return Results(Float.NaN, Float.NaN, 0)
        } catch (e: HttpException) {
            LogcatLogger(0).warning("API", e.message())
            return Results(Float.NaN, Float.NaN, 0)
        }
    }

    suspend fun sendScore(score: Score): Unit {
        try {
            score.computePercentBadCorrect()
            score.computePercentGoodCorrect()
            leaderboardAPI.sendScore(score)
        } catch (e: IOException) {
            LogcatLogger(0).warning("API", e.message ?: "Unknown error")
        } catch (e: HttpException) {
            LogcatLogger(0).warning("API", e.message())
        }
    }
}