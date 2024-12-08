package com.example.mobileuxam

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mobileuxam.databinding.FragmentExamQuestionBinding
import kotlin.random.Random

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

    @SuppressLint("SetTextI18n") // Whoever wrote this check didn't quite pay any attention?
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExamQuestionBinding.inflate(inflater, container, false)
        var ruination = IntArray(0)
        if (Random.nextBoolean()) {
            ruination = IntArray(1)
            ruination[0] = 1
            RuinableConstraintLayout.justMessMyStuffUp = true
        } else {
            RuinableConstraintLayout.justMessMyStuffUp = false
        }

        binding.apply {
            questionNumber.text = (questionNo + 1).toString()
            questionText.text = questions[questionNo]

            buttonTrue.setOnClickListener {
                viewModel.score.results.add(Question(questionNo, ruination, answer == 1))
                nextQuestion()
            }

            buttonFalse.setOnClickListener {
                viewModel.score.results.add(Question(questionNo, ruination, answer == 0))
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