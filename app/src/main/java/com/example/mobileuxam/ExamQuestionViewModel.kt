package com.example.mobileuxam

import androidx.lifecycle.ViewModel

class ExamQuestionViewModel : ViewModel() {
    var score = Score(ArrayList<Question>())
}