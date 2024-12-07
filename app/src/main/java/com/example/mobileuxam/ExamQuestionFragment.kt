package com.example.mobileuxam

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileuxam.databinding.FragmentExamQuestionBinding

class ExamQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = ExamQuestionFragment()
    }

    private val args: ExamQuestionFragmentArgs by navArgs()
    private val viewModel: ExamQuestionViewModel by viewModels()
    private lateinit var answers: IntArray
    private var questions = emptyArray<String>()
    private var questionNo = 0
    private var answer = 1
    private var _binding: FragmentExamQuestionBinding? = null
    private val binding: FragmentExamQuestionBinding
        get() = checkNotNull(_binding) { "View did not inflate!" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questionNo = args.toBundle().getInt("question", 1)
        questions = requireActivity().resources.getStringArray(R.array.questions)
        answers = requireActivity().resources.getIntArray(R.array.answers)
        answer = answers[questionNo]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExamQuestionBinding.inflate(inflater, container, false)
        binding.apply {
            questionNumber.text = (questionNo + 1).toString()
            questionText.text = questions[questionNo]

            buttonTrue.setOnClickListener {
                nextQuestion()
            }

            buttonFalse.setOnClickListener {
                nextQuestion()
            }
        }
        return binding.root
    }

    fun nextQuestion() {
        if (questions.size == questionNo + 1) {
            findNavController().navigate(ExamQuestionFragmentDirections.endExam(viewModel.score))
        } else {
            findNavController().navigate(ExamQuestionFragmentDirections.changeQuestion(questionNo + 1, viewModel.score))
        }
    }
}