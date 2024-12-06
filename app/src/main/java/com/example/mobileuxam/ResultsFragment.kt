package com.example.mobileuxam

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mobileuxam.databinding.FragmentExamQuestionBinding
import com.example.mobileuxam.databinding.FragmentResultsBinding

class ResultsFragment : Fragment() {
    private var _binding: FragmentResultsBinding? = null
    private val binding: FragmentResultsBinding
        get() = checkNotNull(_binding) { "View did not inflate!" }

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private val viewModel: ResultsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        binding.apply {
            results.text = "Score: 50%, Current Global Average: 66%"
            restart.setOnClickListener {
                findNavController().navigate(ResultsFragmentDirections.restart())
            }
        }
        return binding.root
    }
}