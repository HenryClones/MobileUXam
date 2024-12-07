package com.example.mobileuxam

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(val results: Array<Question> = emptyArray()): Parcelable {
    @Json(ignore=true)
    private val goodUx: Int get() {return results.sumOf {q ->
        // This is NOT a useless cast, Kotlin!
        // Expect a GitHub issue because you     can't live without it!
        @Suppress("USELESS_CAST")
        if (q.uxRuination.size == 0) 1 else 0 as Int
    }}
    @Json(ignore=true)
    private val goodUxCorrect: Int get() {return results.sumOf {q ->
        @Suppress("USELESS_CAST")
        if (q.uxRuination.size == 0 && q.correct) 1 else 0 as Int
    }}
    @Json(ignore=true)
    private val badUx:  Int get() {return results.sumOf {q ->
        @Suppress("USELESS_CAST")
        if (q.uxRuination.size >  0) 1 else 0 as Int
    }}
    @Json(ignore=true)
    private val badUxCorrect: Int get() {return results.sumOf {q ->
        @Suppress("USELESS_CAST")
        if (q.uxRuination.size >  0 && q.correct) 1 else 0 as Int
    }}
    @Json(ignore=true)
    val total: Int get() {return badUx + goodUx}
    val percentBadCorrect: Float get() {return badUxCorrect.toFloat() / badUx.toFloat()}
    val percentGoodCorrect: Float get() {return goodUxCorrect.toFloat() / goodUx.toFloat()}

    constructor(parcel: Parcel) : this(parcel.createTypedArray(Question) ?: emptyArray()) {
    }

    override fun describeContents(): Int {
        // Nope, sorry, no file descriptors here!
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelableArray(results, flags)
    }

    companion object CREATOR : Parcelable.Creator<Score> {
        override fun createFromParcel(parcel: Parcel): Score {
            return Score(parcel)
        }

        override fun newArray(size: Int): Array<Score?> {
            return arrayOfNulls(size)
        }
    }
}
