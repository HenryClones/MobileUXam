package com.example.mobileuxam

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ExamQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = ExamQuestionFragment()
    }

    private val viewModel: ExamQuestionViewModel by viewModels()
    private var _binding: View? = null
    private val binding: View = checkNotNull(_binding, { "View did not inflate!" })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflater.inflate(R.layout.fragment_exam_question, container, false)
        binding.apply {

        }
        return binding
    }
}