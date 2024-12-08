package com.example.mobileuxam

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Score(@Json(ignore=true) val results: ArrayList<Question> = ArrayList<Question>()): Parcelable {
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
    var percentBadCorrect: Float = 0.0f;
    fun computePercentBadCorrect() {
        percentBadCorrect = if (badUx > 0) badUxCorrect.toFloat() / badUx.toFloat() * 100 else 0.0f
    }
    var percentGoodCorrect: Float = 0.0f;
    fun computePercentGoodCorrect() {
        percentGoodCorrect = if (goodUx > 0) goodUxCorrect.toFloat() / goodUx.toFloat() * 100 else 0.0f
    }

    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Question) ?: ArrayList<Question>()) {
    }

    override fun describeContents(): Int {
        // Nope, sorry, no file descriptors here!
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelableList(results, flags)
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
