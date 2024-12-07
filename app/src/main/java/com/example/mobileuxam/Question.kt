package com.example.mobileuxam

data class Question(val questionNumber: Int, val uxRuination: IntArray, val correct: Boolean) {
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
}
