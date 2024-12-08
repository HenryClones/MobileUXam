package com.example.mobileuxam

import android.os.Handler.Callback
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ResultsViewModel : ViewModel() {
    private var _results: Results? = null
    private val leaderboardRepository = LeaderboardRepository()
    var results: Score = Score()
    val averageGood: Float get() {return _results?.averageGood ?: Float.NaN}
    val averageBad:  Float get() {return _results?.averageBad  ?: Float.NaN}
    var averageGoodCallback: Runnable? = null
    var averageBadCallback:  Runnable? = null

    init {
        viewModelScope.launch {
            _results = leaderboardRepository.getResults()
            averageBadCallback ?.run()
        }
    }

    fun sendScore() {
        viewModelScope.launch {
            leaderboardRepository.sendScore(results)
        }
    }
}
