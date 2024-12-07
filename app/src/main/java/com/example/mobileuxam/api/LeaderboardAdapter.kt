package com.example.mobileuxam.api

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

class LeaderboardAdapter {
    @FromJson
    fun jsonToTimestamp(ts: String): Date = SimpleDateFormat.getDateTimeInstance().parse(ts) ?: Date()
}