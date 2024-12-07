package com.example.mobileuxam

import android.os.Parcel
import android.os.Parcelable

data class Question(val questionNumber: Int, val uxRuination: IntArray, val correct: Boolean): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.createIntArray() ?: IntArray(0),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (questionNumber != other.questionNumber) return false
        if (!uxRuination.contentEquals(other.uxRuination)) return false
        if (correct != other.correct) return false

        return true
    }

    override fun hashCode(): Int {
        var result = questionNumber
        result = 31 * result + uxRuination.contentHashCode()
        result = 31 * result + correct.hashCode()
        return result
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(questionNumber)
        dest.writeIntArray(uxRuination)
        dest.writeBoolean(correct)
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
