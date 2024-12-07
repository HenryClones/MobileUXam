package com.example.mobileuxam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {
    private val _averageGood: Float? = null
    private val _averageBad:  Float? = null
    private val leaderboardRepository = LeaderboardRepository()
    val averageGood: Float get() {return _averageGood ?: 0f}
    val averageBad:  Float get() {return _averageBad  ?: 0f}
    init {
        viewModelScope.launch {
            leaderboardRepository.getAverageBad()
        }
        viewModelScope.launch {
            leaderboardRepository.getAverageGood()
        }
    }

    fun sendScore(score: Score) {
        viewModelScope.launch {
            leaderboardRepository.sendScore(score)
        }
    }
}
