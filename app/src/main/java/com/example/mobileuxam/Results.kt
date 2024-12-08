package com.example.mobileuxam

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Results(val averageGood: Float, val averageBad: Float, val numScores: Int)
